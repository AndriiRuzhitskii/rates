package com.example.services;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.DemoApplication;
import com.example.models.Asset;
import com.example.models.Currency;
import com.example.models.Rate;

@Service
public class OpportunityCostsServiceImpl implements OpportunityCostsService {

	private static final Logger log = LoggerFactory
			.getLogger(DemoApplication.class);

	public static final List<String> defaultCurrencies = Collections
			.unmodifiableList(Arrays.asList("USD", "EUR", "RUB", "XAU", "XAG", "GBP"));

	@Autowired
	private RateService rateService;

	@Autowired
	private CurrencyService currencyService;

	@Override
	public String getCostByToday(Asset asset, String cc, UserDetails user) {
		Currency currency = currencyService.getCurrencyByCc(cc);
		float assetAmount = asset.getAmount();
		Rate rateByAssetDate = rateService.getRateByDateFromDb(asset.getDate(),
				currency);
		Rate rateByCurrentDate = rateService.getRateByDateFromDb(
				LocalDate.now(), currency);
		return calculateCost(assetAmount, rateByAssetDate, rateByCurrentDate);
	}

	@Override
	public String getCostByDate(Asset asset, String cc, String date,
			UserDetails user) {
		Currency currency = currencyService.getCurrencyByCc(cc);
		float assetAmount = asset.getAmount();
		Rate rateByAssetDate = rateService.getRateByDateFromDb(asset.getDate(),
				currency);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate localDate = LocalDate.parse(date, formatter);
		Rate rateByDate = rateService.getRateByDateFromDb(localDate, currency);
		return calculateCost(assetAmount, rateByAssetDate, rateByDate);
	}

	@Override
	public Map<String, String> getCostByTodayAll(Asset asset, String cc,
			UserDetails user) {
		return calculateCostAll(asset, user, defaultCurrencies);
	}

	@Override
	public Map<String, String> getCostByDateAll(Asset asset, String cc,
			String date, UserDetails user) {
		return calculateCostByDateAll(asset, user, date, defaultCurrencies);
	}

	private String calculateCost(float assetAmount, Rate rateByAssetDate,
			Rate rateByDate) {
		float ratePast = rateByAssetDate.getRate();
		float rateCurrrent = rateByDate.getRate();
		float rateDiff = rateCurrrent - ratePast;
		float currencyAmount = assetAmount / ratePast;
		float opportunity = currencyAmount * rateDiff;

		log.info("----------");
		log.info("ratePast = " + ratePast);
		log.info("rateCurrrent = " + rateCurrrent);
		log.info("rateDiff = " + rateDiff);
		log.info("currencyAmount = " + currencyAmount);
		log.info("opportunity = " + opportunity);

		return Float.toString(opportunity);
	}

	private Map<String, String> calculateCostAll(Asset asset, UserDetails user,
			List<String> currencies) {

		Map<String, String> map = new HashMap();
		map = currencies.stream().collect(
				Collectors.toMap(x -> x, x -> getCostByToday(asset, x, user)));
		return map;
	}

	private Map<String, String> calculateCostByDateAll(Asset asset,
			UserDetails user, String date, List<String> currencies) {
		Map<String, String> map = new HashMap();
		map = currencies.stream().collect(
				Collectors.toMap(x -> x,
						x -> getCostByDate(asset, x, date, user)));
		return map;
	}

	private Map<String, String> calculateOpportunityCostByToday(Asset asset,
			UserDetails user) {

		Map<String, String> original = new HashMap();
		original = calculateCostAll(asset, user, defaultCurrencies);

		String cost = original.get(asset.getCurrency().getCc());

		Map<String, String> copy = original
				.entrySet()
				.stream()
				.collect(
						Collectors.toMap(
								Map.Entry::getKey,
								e -> Float.toString(Float.valueOf(e.getValue())
										- Float.valueOf(cost))));

		return copy;
	}

	private Map<String, String> calculateOpportunityCostByDate(Asset asset,
			String cc, UserDetails user, String date) {
		Map<String, String> original = new HashMap();
		original = calculateCostByDateAll(asset, user, date, defaultCurrencies);

		String cost = original.get(asset.getCurrency().getCc());

		Map<String, String> copy = original
				.entrySet()
				.stream()
				.collect(
						Collectors.toMap(
								Map.Entry::getKey,
								e -> Float.toString(Float.valueOf(e.getValue())
										- Float.valueOf(cost))));

		return copy;
	}

	private Map<String, Map<String, String>> calculateOpportunityCostForAsset(
			Asset asset, UserDetails user, LocalDate today, LocalDate pastday) {

		Map<String, Map<String, String>> map = new HashMap();
		List<String> dates = new ArrayList();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		long days = Duration.between(pastday.atTime(0, 0), today.atTime(0, 0))
				.toDays();
		for (long i = 0; i < days; i++) {
			LocalDate d = pastday.plusDays(i);
			dates.add(d.format(formatter));
		}
		map = dates.stream().collect(
				Collectors.toMap(
						x -> x,
						x -> calculateOpportunityCostByDate(asset, asset
								.getCurrency().getCc(), user, x)));
		return map;
	}

	@Override
	public Map<String, String> getOpportunityByTodayAll(Asset asset, String cc,
			UserDetails user) {
		return calculateOpportunityCostByToday(asset, user);
	}

	@Override
	public Map<String, String> getOpportunityByDateAll(Asset asset, String cc,
			String date, UserDetails user) {
		return calculateOpportunityCostByDate(asset, cc, user, date);
	}

	@Override
	public Map<String, Map<String, String>> getOpportunity(Asset asset,
			UserDetails user) {

		LocalDateTime now = LocalDateTime.now();
		LocalDate today = now.toLocalDate();
		LocalDate pastday = asset.getDate();
		Map<String, Map<String, String>> map = new HashMap();

		map = calculateOpportunityCostForAsset(asset, user, today, pastday);

		// List<String> dates = new ArrayList();
		// DateTimeFormatter formatter =
		// DateTimeFormatter.ofPattern("yyyyMMdd");
		// long days = Duration.between(pastday.atTime(0, 0), today.atTime(0,
		// 0))
		// .toDays();
		// for (long i = 0; i < days; i++) {
		// LocalDate d = pastday.plusDays(i);
		// dates.add(d.format(formatter));
		// }
		//
		// map = dates.stream().collect(
		// Collectors.toMap(
		// x -> x,
		// x -> calculateOpportunityCostByDate(asset, asset
		// .getCurrency().getCc(), user, x)));

		return map;
	}

	@Override
	public Map<String, Map<String, String>> getOpportunityByDate(Asset asset,
			String date, UserDetails user) {
		LocalDateTime now = LocalDateTime.now();
		
//		LocalDate today = now.toLocalDate();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate today = LocalDate.parse(date, formatter);
		
		LocalDate pastday = asset.getDate();
		Map<String, Map<String, String>> map = new HashMap();

		map = calculateOpportunityCostForAsset(asset, user, today, pastday);
		return map;
	}

}

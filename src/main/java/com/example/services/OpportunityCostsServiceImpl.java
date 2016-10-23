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
	public String getCostByToday(Asset asset, String cc) {
		Currency currency = currencyService.getCurrencyByCc(cc);
		float assetAmount = asset.getAmount();
		Rate rateByAssetDate = rateService.getRateByDateFromDb(asset.getDate(),
				currency);
		Rate rateByCurrentDate = rateService.getRateByDateFromDb(
				LocalDate.now(), currency);
		log.info("cc = " + cc);
		return calculateCost(assetAmount, rateByAssetDate, rateByCurrentDate);
	}

	@Override
	public String getCostByDate(Asset asset, String cc, String date) {
		Currency currency = currencyService.getCurrencyByCc(cc);
		float assetAmount = asset.getAmount();
		Rate rateByAssetDate = rateService.getRateByDateFromDb(asset.getDate(),
				currency);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate localDate = LocalDate.parse(date, formatter);
		Rate rateByDate = rateService.getRateByDateFromDb(localDate, currency);
		log.info("cc = " + cc);
		return calculateCost(assetAmount, rateByAssetDate, rateByDate);
	}

	@Override
	public Map<String, String> getCostByTodayAll(Asset asset) {
		return calculateCostAll(asset);
	}

	@Override
	public Map<String, String> getCostByDateAll(Asset asset,
			String date) {
		return calculateCostByDateAll(asset, date);
	}

	@Override
	public Map<String, String> getOpportunityByTodayAll(Asset asset) {

		Map<String, String> original = new HashMap();
		original = calculateCostAll(asset);

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

	@Override
	public Map<String, String> getOpportunityByDateAll(Asset asset,
			String date) {
		return calculateOpportunityCostByDate(asset, date);
	}

	@Override
	public Map<String, Map<String, String>> getOpportunity(Asset asset) {

		LocalDateTime now = LocalDateTime.now();
		LocalDate today = now.toLocalDate();
		LocalDate pastday = asset.getDate();
		Map<String, Map<String, String>> map = new HashMap();

		map = calculateOpportunityCostForAsset(asset, today, pastday);

		return map;
	}

	@Override
	public Map<String, Map<String, String>> getOpportunityByDate(Asset asset,
			String date) {
		LocalDateTime now = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate today = LocalDate.parse(date, formatter);
		
		LocalDate pastday = asset.getDate();
		Map<String, Map<String, String>> map = new HashMap();

		map = calculateOpportunityCostForAsset(asset, today, pastday);
		return map;
	}
	
	private String calculateCost(float assetAmount, Rate rateByAssetDate,
			Rate rateByDate) {
		float ratePast = rateByAssetDate.getRate();
		float rateCurrrent = rateByDate.getRate();
		float rateDiff = rateCurrrent - ratePast;
		float currencyAmount = assetAmount / ratePast;
		float cost = currencyAmount * rateDiff;

		log.info("----------");
		log.info("ratePast = " + ratePast);
		log.info("rateCurrrent = " + rateCurrrent);
		log.info("rateDiff = " + rateDiff);
		log.info("currencyAmount = " + currencyAmount);
		log.info("cost = " + cost);

		return Float.toString(cost);
	}
	
	private String calculateOpportunity(String costOfAsset, String alternativeCost) {
		float opportunity = Float.valueOf(alternativeCost) - Float.valueOf(costOfAsset);
		return Float.toString(opportunity);
	}

	private Map<String, String> calculateCostAll(Asset asset) {

		Map<String, String> map = new HashMap();
		map = defaultCurrencies.stream().collect(
				Collectors.toMap(x -> x, x -> getCostByToday(asset, x)));
		return map;
	}

	private Map<String, String> calculateCostByDateAll(Asset asset, String date) {
		Map<String, String> map = new HashMap();
		map = defaultCurrencies.stream().collect(
				Collectors.toMap(x -> x,
						x -> getCostByDate(asset, x, date)));
		return map;
	}

	private Map<String, String> calculateOpportunityCostByDate(Asset asset,
			String date) {
		Map<String, String> costs = new HashMap();
		costs = calculateCostByDateAll(asset, date);
		String cost = costs.get(asset.getCurrency().getCc());
		Map<String, String> opportunities = costs
				.entrySet()
				.stream()
				.collect(
						Collectors.toMap(
								Map.Entry::getKey,
								e -> calculateOpportunity(e.getValue(), cost)));
		return opportunities;
	}

	private Map<String, Map<String, String>> calculateOpportunityCostForAsset(
			Asset asset, LocalDate today, LocalDate pastday) {

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
						x -> calculateOpportunityCostByDate(asset, x)));
		return map;
	}

}

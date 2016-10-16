package com.example.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.DemoApplication;
import com.example.controllers.RateController;
import com.example.models.Asset;
import com.example.models.Currency;
import com.example.models.Rate;

@Service
public class OpportunityCostsServiceImpl implements OpportunityCostsService {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
		
	@Autowired 
	private RateService rateService; 
	
	@Autowired 
	private CurrencyService currencyService; 
	
	@Override
	public String getOpportunityCostByToday(Asset asset, String cc, UserDetails user) {
		Currency currency = currencyService.getCurrencyByCc(cc);
		float assetAmount = asset.getAmount();
		Rate rateByAssetDate = rateService.getRateByDateFromDb(asset.getDate(), currency);
		Rate rateByCurrentDate = rateService.getRateByDateFromDb(LocalDate.now(), currency);
		return calculateOpportunity(assetAmount, rateByAssetDate, rateByCurrentDate);
	}

	@Override
	public String getOpportunityCostByDate(Asset asset, String cc, String date,
			UserDetails user) {
		Currency currency = currencyService.getCurrencyByCc(cc);
		float assetAmount = asset.getAmount();
		Rate rateByAssetDate = rateService.getRateByDateFromDb(asset.getDate(), currency);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate localDate = LocalDate.parse(date, formatter);
		Rate rateByDate = rateService.getRateByDateFromDb(localDate, currency);
		return calculateOpportunity(assetAmount, rateByAssetDate, rateByDate);
	}
	
	private String calculateOpportunity(float assetAmount,
			Rate rateByAssetDate,
			Rate rateByDate){
		float ratePast = rateByAssetDate.getRate();
		float rateCurrrent = rateByDate.getRate();
		float rateDiff = rateCurrrent - ratePast;
		float opportunity = assetAmount * rateDiff;
		
		log.info("ratePast = " + ratePast);
		log.info("rateCurrrent = " + rateCurrrent);
		log.info("rateDiff = " + rateDiff);
		log.info("opportunity = " + opportunity);
		
		return Float.toString(opportunity);
	}

}

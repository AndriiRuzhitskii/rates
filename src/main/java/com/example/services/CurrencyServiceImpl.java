package com.example.services;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DemoApplication;
import com.example.models.Currency;
import com.example.models.Rate;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	@Autowired
	private RateService rateService; 
	
	@Override
	public List<Currency> getAllCurrenciesOngoingDate() {
		
		List<Rate> rates = rateService.getAllRatesByOngoingDate();
		rates.forEach(System.out::println);
		return null;
	}

	@Override
	public boolean saveCurrenciesToDb(List<Currency> currencies) {
		// TODO Auto-generated method stub
		return true;
	}
	
}

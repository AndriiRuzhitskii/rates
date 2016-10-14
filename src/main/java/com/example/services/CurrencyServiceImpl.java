package com.example.services;

import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DemoApplication;
import com.example.converters.RateNbuConverter;
import com.example.models.Currency;
import com.example.models.CurrencyDao;
import com.example.models.RateNbu;

@Service
public class CurrencyServiceImpl implements CurrencyService {
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	@Autowired
	private RateService rateService; 
	
	@Autowired
	private CurrencyDao currencyDao;
	
	@Resource
	private RateNbuConverter rateNbuConverter;
	
	@Override
	public List<Currency> getAllCurrenciesOngoingDate() {
		List<Currency> currencys;
		List<RateNbu> rates = rateService.getAllRatesByOngoingDate();	
		currencys = rateNbuConverter.convertList(rates);
		return currencys;
	}

	@Override
	public boolean saveCurrenciesToDb(List<Currency> currencies) {
		currencies.stream().forEach(d -> currencyDao.save(d));
		return true;
	}

	@Override
	public boolean getCurrencies() {
		log.info("getCurrencies()");
		saveCurrenciesToDb(getAllCurrenciesOngoingDate());
		return true;
	}

	@Override
	public List<Currency> getCurrenciesFromDb() {
		return (List<Currency>) currencyDao.findAll();
	}
	
}

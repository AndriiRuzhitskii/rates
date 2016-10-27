package com.example.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.example.models.Currency;
import com.example.models.Rate;
import com.example.models.RateDao;
import com.example.models.RateNbu;

public interface RateService {
	public List<Rate> findAll();
	
	public List<Rate> findByExchangedate(String exchangedate);
	
	public List<Rate> findByCc(String cc);
	
	public Long getAverage(String cc);
	
	public Rate getMax(String cc);
	
	public Rate getMin(String cc);
	
//	public Rate getRateByDate(String date, String cc);
	
	public Rate getRateByDate(String date, String cc);
	
	public void setRepository(RateDao repository);

	public RateDao getRepository();

	public Map<String, Rate> getAllRatesByDate(LocalDate start, LocalDate end, String cc);
	
	public Set<RateNbu> getAllRatesByOngoingDate();
		
	public Rate getRateByDateFromDb(LocalDate date, Currency currency);
}

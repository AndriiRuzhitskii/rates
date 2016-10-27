package com.example.services;

import java.util.List;
import java.util.Set;

import com.example.models.Currency;

public interface CurrencyService {
	public Set<Currency> getAllCurrenciesOngoingDate();
	public boolean saveCurrenciesToDb(Set<Currency> currencies); 
	public boolean getCurrencies();
	public List<Currency> getCurrenciesFromDb();
	public Currency getCurrencyByCc(String cc);
}

package com.example.services;

import java.util.List;
import com.example.models.Currency;

public interface CurrencyService {
	public List<Currency> getAllCurrenciesOngoingDate();
	public boolean saveCurrenciesToDb(List<Currency> currencies); 
	public boolean getCurrencies();
	public List<Currency> getCurrenciesFromDb();
}

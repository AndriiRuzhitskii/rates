package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Currency;
import com.example.services.CurrencyService;

@RestController
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;
	
	@RequestMapping("/currencys")
	List<Currency> getCurrencys(){
		return currencyService.getCurrenciesFromDb();
	}
	
}

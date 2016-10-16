package com.example.deserializers;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.models.Currency;
import com.example.services.CurrencyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CurrencyDeserializer extends
		JsonDeserializer<Currency> {

	@Autowired
	private CurrencyService currencyService;
	
	@Override
	public Currency deserialize(
			com.fasterxml.jackson.core.JsonParser arg0,
			DeserializationContext arg1) throws IOException,
			JsonProcessingException {
//		System.out.println("Currency deserialize()");
		String str = arg0.getText().trim();
		Currency currency = currencyService.getCurrencyByCc(str);
		return currency;

	}

}
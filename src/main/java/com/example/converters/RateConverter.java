package com.example.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.models.Currency;
import com.example.models.CurrencyDao;
import com.example.models.Rate;
import com.example.models.RateNbu;

@Component("rateConverter")
public final class RateConverter implements Converter<RateNbu, Rate> {

	@Autowired
	private CurrencyDao currencyRepository;
	
	@Override
	public Rate convert(RateNbu source) {
			Rate target = new Rate();
			target.setRate(source.getRate());
			target.setExchangedate(source.getExchangedate());
	    	Currency currency = currencyRepository.findByCc(source.getCc());
	    	target.setCurrency(currency);
			return target;	
	}
//	
//	public List<Rate> convertList(final List<RateNbu> source) {
//
//	    final List<Rate> dest = new ArrayList();
//
//	    if (source != null) {
//	        for (final RateNbu element : source) {
//	            dest.add(this.convert(element));
//	        }
//	    }
//	    return dest;
//	}

}

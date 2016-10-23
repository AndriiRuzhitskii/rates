package com.example.converters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

@Component("rateNbuConverter")
public final class RateNbuConverter implements Converter<RateNbu, Currency> {

	@Autowired
	private CurrencyDao currencyRepository;
	
	@Override
	public Currency convert(RateNbu source) {
			Currency target = new Currency();
			target.setCc(source.getCc());
			target.setR030(source.getR030());
			target.setTxt(source.getTxt());
			return target;	
	}
	
//	public List<Currency> convertList(final List<RateNbu> source) {
//
//	    final List<Currency> dest = new ArrayList();
//
//	    if (source != null) {
//	        for (final RateNbu element : source) {
//	            dest.add(this.convert(element));
//	        }
//	    }
//	    return dest;
//	}

	public List<Currency> convertList (final List<RateNbu> source) {
	    List<Currency> dest = new ArrayList();
	    Map<RateNbu, Currency> result = new HashMap();
	    result.putAll(source.stream().collect(Collectors.toMap(d -> d, d -> this.convert(d))));
	    dest = result.entrySet().stream().map(map -> map.getValue())
                .collect(Collectors.toList());
	    return dest;
	}
	
}

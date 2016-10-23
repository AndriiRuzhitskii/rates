package com.example.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.DemoApplication;
import com.example.converters.RateConverter;
import com.example.models.Currency;
import com.example.models.CurrencyDao;
import com.example.models.Rate;
import com.example.models.RateDao;
import com.example.models.RateNbu;

@Service
public class RateServiceImpl implements RateService {
	
	private static final String NBU_URL = "http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";
	private static final String NBU_URL_ONGOING_DATE = "http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
	private static StringBuilder sb = new StringBuilder();
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	@Autowired
	private RateDao repository;
	
	@Autowired
	private CurrencyDao currencyRepository;

	@Resource
	private RateConverter rateConverter;
	
	public List<Rate> findAll() {
		List<Rate> rates = (List<Rate>) repository.findAll();
		return rates;
	}
	
	public List<Rate> findByExchangedate(String exchangedate) {
		List<Rate> rates = (List<Rate>) repository.findByExchangedate(exchangedate);
		return rates;
	}
	
	public List<Rate> findByCc(String cc) {
//		List<Rate> rates = (List<Rate>) repository.findByCc(cc);
		List<Rate> rates = new ArrayList();
		return rates;
	}
	
	public Long getAverage(String cc) {
//		Long avg = repository.getAverage(cc);
		Long avg = 0l;
		return avg;
	}
	
	public Rate getMax(String cc) {
//		Rate rate = repository.getMax(cc);
		Rate rate = new Rate();
		return rate;
	}
	
	public Rate getMin(String cc) {
//		Rate rate = repository.getMin(cc);
		Rate rate = new Rate();
		return rate;
	}
	
//	public Rate getRateByDate(String date, String cc) {
//		Rate r;
//		Rate[] rate = {};
//		RestTemplate restTemplate = new RestTemplate();
//		try {
//    		rate = restTemplate.getForObject(getUrl(date, cc), Rate[].class);
//        } catch (HttpStatusCodeException e) {
//        	r = new Rate();
//        } catch (RuntimeException e) {
//        	r = new Rate();
//        }
//    	if(rate.length == 0) {
//    		r = new Rate();
//    	} else {
//			r = rate[0];
//    	}
//    	Currency currency = new Currency();
//    	currency.setR030((short)840);
//    	currency.setTxt("Долар США");
//    	currency.setCc("USD");
//    	currency.setRates(new ArrayList());
//    	// {"r030":840,"txt":"Долар США","cc":"USD""currency":null}
//    	
//    	currencyRepository.save(currency);
//    	
//    	r.setCurrency(currency);
//		return r;	
//	}
	
	private String getUrl(String date, String cc) {
		sb.delete( 0, sb.length() );
		sb.append( NBU_URL );
        sb.append( "?valcode=" );
        sb.append( cc );
        sb.append( "&date=" );      
        sb.append( date );
        sb.append( "&json" );
//        log.info("getUrl() = " + sb.toString()); 
		return sb.toString();	
	}
	
	public Map<String, Rate> getAllRatesByDate(LocalDate start, LocalDate end, String cc) {
		
//		log.info("getAllRatesByDate() cc = " + cc);
		
		Map<String, Rate> result = new HashMap();
		
		Stream<LocalDate> stream = LongStream.range(start.toEpochDay(),
				end.toEpochDay() + 1).mapToObj(LocalDate::ofEpochDay);
		
		List<String> dateList = stream
				.map(d -> d.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
						.toString()).distinct()
				.collect(Collectors.toList());
		
//		result.putAll(dateList.stream().collect(Collectors.toMap(d -> d, d -> getRateByDate(d, cc))));
		result.putAll(dateList.stream().collect(Collectors.toMap(d -> d, d -> getRateByDate(d, cc))));
		// TODO May be change output type
		return result;
	}
	
	public boolean saveRatesToDb(Map<String, Rate> rates) {
		
		log.info("saveRatesToDb() rates.size() = " + rates.size());
		
		// TODO May be change input param type
		List<Rate> list = rates.entrySet().stream().map(map -> map.getValue())
                .collect(Collectors.toList());
		list.stream().forEach(System.out::println);
		list.stream().forEach(d -> repository.save(d));

		return true; // TODO Change return
	}
	

	public RateDao getRepository() {
		return repository;
	}

	public void setRepository(RateDao repository) {
		this.repository = repository;
	}

	public void getNbuRates() {
		log.info("getNbuRates()");
		LocalDate start = LocalDate.parse("2016-10-10"), // 1998-01-01
		end = LocalDate.now();
		saveRatesToDb(getAllRatesByDate(start, end, "USD"));
		saveRatesToDb(getAllRatesByDate(start, end, "EUR"));
		saveRatesToDb(getAllRatesByDate(start, end, "RUB"));
		saveRatesToDb(getAllRatesByDate(start, end, "XAU"));
		saveRatesToDb(getAllRatesByDate(start, end, "XAG"));
		saveRatesToDb(getAllRatesByDate(start, end, "GBP"));
	}

	@Override
	public List<RateNbu> getAllRatesByOngoingDate() {
		
		RateNbu[] rateNbu = {};
		RestTemplate restTemplate = new RestTemplate();
		try {
    		rateNbu = restTemplate.getForObject(NBU_URL_ONGOING_DATE, RateNbu[].class);
    		log.info("rateNbu " + rateNbu); 
    	} catch (RuntimeException e) {
        	return new ArrayList<RateNbu>();
        }
		
		List<RateNbu> listNbu = Arrays.asList(rateNbu);
		
		return listNbu;
	}

	@Override
	public Rate getRateByDate(String date, String cc) {
		
//		log.info("getRateByDate() date = " + date + " cc = " + cc); 
		
		RateNbu r;
		RateNbu[] rate = {};
		RestTemplate restTemplate = new RestTemplate();
		try {
    		rate = restTemplate.getForObject(getUrl(date, cc), RateNbu[].class);
        } catch (RuntimeException e) {
        	r = new RateNbu();
        }
    	if(rate.length == 0) {
    		r = new RateNbu();
    	} else {
			r = rate[0];
    	}    
    	Rate result = rateConverter.convert(r);
		return result;	
	}

	@Override
	public Rate getRateByDateFromDb(LocalDate date, Currency currency) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String formattedString = date.format(formatter);	
		return repository.findByExchangedateAndCurrency(formattedString, currency);
	}

}

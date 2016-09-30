package com.example.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.example.DemoApplication;
import com.example.models.Rate;
import com.example.models.RateDao;

@Service
public class RateServiceImpl implements RateService {
	
	private static final String NBU_URL = "http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";
	private static StringBuilder sb = new StringBuilder();
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	@Autowired
	private RateDao repository;
	
	public List<Rate> findAll() {
		List<Rate> rates = (List<Rate>) repository.findAll();
		return rates;
	}
	
	public List<Rate> findByExchangedate(String exchangedate) {
		List<Rate> rates = (List<Rate>) repository.findByExchangedate(exchangedate);
		return rates;
	}
	
	public List<Rate> findByCc(String cc) {
		List<Rate> rates = (List<Rate>) repository.findByCc(cc);
		return rates;
	}
	
	public Long getAverage(String cc) {
		Long avg = repository.getAverage(cc);
		return avg;
	}
	
	public Rate getMax(String cc) {
		Rate rate = repository.getMax(cc);
		return rate;
	}
	
	public Rate getMin(String cc) {
		Rate rate = repository.getMin(cc);
		return rate;
	}
	
	public Rate getRateByDate(String date, String cc) {
		Rate r;
		Rate[] rate = {};
		RestTemplate restTemplate = new RestTemplate();
		try {
    		rate = restTemplate.getForObject(getUrl(date, cc), Rate[].class);
        } catch (HttpStatusCodeException e) {
        	r = new Rate();
        } catch (RuntimeException e) {
        	r = new Rate();
        }
    	if(rate.length == 0) {
    		r = new Rate();
    	} else {
			r = rate[0];
    	}
		return r;	
	}
	
	private String getUrl(String date, String cc) {
		sb.delete( 0, sb.length() );
		sb.append( NBU_URL );
        sb.append( "?valcode=" );
        sb.append( cc );
        sb.append( "&date=" );      
        sb.append( date );
        sb.append( "&json" );
		return sb.toString();	
	}
	
	public Map<String, Rate> getAllRatesByDate(LocalDate start, LocalDate end, String cc) {
		
		Map<String, Rate> result = new HashMap();
		
		Stream<LocalDate> stream = LongStream.range(start.toEpochDay(),
				end.toEpochDay() + 1).mapToObj(LocalDate::ofEpochDay);
		
		List<String> dateList = stream
				.map(d -> d.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
						.toString()).distinct()
				.collect(Collectors.toList());
		
		result.putAll(dateList.stream().collect(Collectors.toMap(d -> d, d -> getRateByDate(d, cc))));
		
		return result;
	}
	
	public boolean saveRatesToDb(Map<String, Rate> rates) {
		List<Rate> list = rates.entrySet().stream().map(map -> map.getValue())
                .collect(Collectors.toList());
//		list.stream().forEach(System.out::println);
		list.stream().forEach(d -> repository.save(d));

		return true;
	}
	

	public RateDao getRepository() {
		return repository;
	}

	public void setRepository(RateDao repository) {
		this.repository = repository;
	}

	public void getNbuRates() {
		log.info("getNbuRates()");
		LocalDate start = LocalDate.parse("2016-09-20"), // 1998-01-01
		end = LocalDate.now();
		saveRatesToDb(getAllRatesByDate(start, end, "USD"));
		saveRatesToDb(getAllRatesByDate(start, end, "EUR"));
		saveRatesToDb(getAllRatesByDate(start, end, "RUB"));
		saveRatesToDb(getAllRatesByDate(start, end, "XAU"));
		saveRatesToDb(getAllRatesByDate(start, end, "XAG"));
	}
}

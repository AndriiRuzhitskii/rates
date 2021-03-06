package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.services.CurrencyService;
import com.example.services.RateServiceImpl;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private RateServiceImpl rateService;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			currencyService.getCurrencies();
			rateService.getNbuRates();
		};
	}
	
//	@Bean
//	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//	    ObjectMapper mapper = new ObjectMapper();
//	    mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//	    MappingJackson2HttpMessageConverter converter = 
//	        new MappingJackson2HttpMessageConverter(mapper);
//	    return converter;
//	}
	
}

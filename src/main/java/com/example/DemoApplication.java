package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.services.AccountService;
import com.example.services.RateServiceImpl;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private RateServiceImpl rateService;
	
	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo() {
		return (args) -> {
			rateService.getNbuRates();
			accountService.createAccountOnInit();
		};
	}
	
}

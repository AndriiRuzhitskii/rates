package com.example.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoApplication;
import com.example.Utilities;
import com.example.models.Rate;
import com.example.models.RateDao;
import com.example.services.RateService;

@RestController
public class RateController {
	
	private static final String NBU_URL = "http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange";
	private static StringBuilder sb = new StringBuilder();
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	@Autowired
	private RateDao repository;
	
	@Autowired
	private RateService service;
    
    @RequestMapping("/rate")
    List<Rate> getRate() {
    	List<Rate> rates = (List<Rate>) service.findAll();
    	return rates;
    }
    
    @RequestMapping("/ratebyname")
    List<Rate> getRateByName(@RequestParam(value="name", defaultValue="USD") String name) {
    	List<Rate> rates = (List<Rate>) service.findByCc(name);
    	return rates;
    }
      
    @RequestMapping("/ratebydate")
    List<Rate> getRateByDate(@RequestParam(value="date", defaultValue="01.01.2016") String date) {
    	System.out.println("date = " + date);
    	if(Utilities.isValidFormat("dd.MM.yyyy", date)) {
    		List<Rate> rates = (List<Rate>) service.findByExchangedate(date);
    		return rates;
    	} else {
    		return null;
    	}
    }
    
    @RequestMapping("/rateaverage")
    Long getRateAverage(@RequestParam(value="name", defaultValue="USD") String name) {
    	Long avg = service.getAverage(name);
    	return avg;
    }
    
    @RequestMapping("/ratemin")
    Rate getRateMin(@RequestParam(value="name", defaultValue="USD") String name) {
    	Rate rate = service.getMin(name);
    	return rate;
    }
    
    @RequestMapping("/ratemax")
    Rate getRateMax(@RequestParam(value="name", defaultValue="USD") String name) {
    	Rate rate = service.getMax(name);
    	return rate;
    }
    
}

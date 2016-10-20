package com.example.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Asset;
import com.example.services.OpportunityCostsService;

@RestController
public class OpportunityCostsController {

	@Autowired
	private OpportunityCostsService opportunityCostsService;

	@RequestMapping("/cost")
	String getCost(@RequestBody Asset asset,
			@RequestParam(value="cc") String cc,
			@RequestParam(value="date") String date) {
		return opportunityCostsService.getCostByDate(asset, cc, date);
	}

	@RequestMapping("/costtoday")
	String getCostToday(@RequestBody Asset asset,
			@RequestParam(value="cc") String cc) {
		return opportunityCostsService.getCostByToday(asset, cc);
	}
	
	@RequestMapping("/costall")
	Map<String, String> getCostAll(@RequestBody Asset asset,
			@RequestParam(value="cc") String cc,
			@RequestParam(value="date") String date) {
		return opportunityCostsService.getCostByDateAll(asset, date);
	}

	@RequestMapping("/costtodayall")
	Map<String, String> getCostTodayAll(@RequestBody Asset asset) {
		return opportunityCostsService.getCostByTodayAll(asset);
	}
	
	@RequestMapping("/opportunitytodayall")
	Map<String, String> getOpportunityByTodayAll(@RequestBody Asset asset) {
		return opportunityCostsService.getOpportunityByTodayAll(asset);
	}
	
	@RequestMapping("/opportunitybydateall")
	Map<String, String> getOpportunityByDateAll(@RequestBody Asset asset,
			@RequestParam(value="date") String date) {
		return opportunityCostsService.getOpportunityByDateAll(asset, date);
	}
	
	@RequestMapping("/opportunity")
	Map<String, Map<String, String>> getOpportunity(@RequestBody Asset asset) {
		return opportunityCostsService.getOpportunity(asset);
	}
	
	@RequestMapping("/opportunitybydate")
	Map<String, Map<String, String>> getOpportunity(@RequestBody Asset asset,
			@RequestParam(value="date") String date) {
		return opportunityCostsService.getOpportunityByDate(asset, date);
	}
}

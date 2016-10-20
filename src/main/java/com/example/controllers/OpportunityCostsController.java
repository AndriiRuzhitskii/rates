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

//	@RequestParam(value="cc", defaultValue="USD") String cc,
//	@RequestParam(value="date", defaultValue="20161013") String date,
	
	@RequestMapping("/cost")
	String getCost(@RequestBody Asset asset,
			@RequestParam(value="cc") String cc,
			@RequestParam(value="date") String date,
			@AuthenticationPrincipal final UserDetails user) {
		return opportunityCostsService.getCostByDate(asset, cc, date, user);
	}

	@RequestMapping("/costtoday")
	String getCostToday(@RequestBody Asset asset,
			@RequestParam(value="cc") String cc,
			@AuthenticationPrincipal final UserDetails user) {
		return opportunityCostsService.getCostByToday(asset, cc, user);
	}
	
	@RequestMapping("/costall")
	Map<String, String> getCostAll(@RequestBody Asset asset,
			@RequestParam(value="cc") String cc,
			@RequestParam(value="date") String date,
			@AuthenticationPrincipal final UserDetails user) {
		return opportunityCostsService.getCostByDateAll(asset, cc, date, user);
	}

	@RequestMapping("/costtodayall")
	Map<String, String> getCostTodayAll(@RequestBody Asset asset,
			@RequestParam(value="cc") String cc,
			@AuthenticationPrincipal final UserDetails user) {
		return opportunityCostsService.getCostByTodayAll(asset, cc, user);
	}
	
	@RequestMapping("/opportunitytodayall")
	Map<String, String> getOpportunityByTodayAll(@RequestBody Asset asset,
			@RequestParam(value="cc") String cc,
			@AuthenticationPrincipal final UserDetails user) {
		return opportunityCostsService.getOpportunityByTodayAll(asset, cc, user);
	}
	
	@RequestMapping("/opportunitybydateall")
	Map<String, String> getOpportunityByDateAll(@RequestBody Asset asset,
			@RequestParam(value="cc") String cc,
			@RequestParam(value="date") String date,
			@AuthenticationPrincipal final UserDetails user) {
		return opportunityCostsService.getOpportunityByDateAll(asset, cc, date, user);
	}
	
	@RequestMapping("/opportunity")
	Map<String, Map<String, String>> getOpportunity(@RequestBody Asset asset,
			@AuthenticationPrincipal final UserDetails user) {
		return opportunityCostsService.getOpportunity(asset, user);
	}
	
	@RequestMapping("/opportunitybydate")
	Map<String, Map<String, String>> getOpportunity(@RequestBody Asset asset,
			@RequestParam(value="date") String date,
			@AuthenticationPrincipal final UserDetails user) {
		return opportunityCostsService.getOpportunityByDate(asset, date, user);
	}
}

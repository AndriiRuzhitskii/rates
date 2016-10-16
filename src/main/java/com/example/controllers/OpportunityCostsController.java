package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Account;
import com.example.models.Asset;
import com.example.services.OpportunityCostsService;

@RestController
public class OpportunityCostsController {

	@Autowired
	private OpportunityCostsService opportunityCostsService;

	@RequestMapping("/opportunity")
	String getOpportunity(@RequestBody Asset asset,
			@RequestParam(value="cc", defaultValue="USD") String cc,
			@RequestParam(value="date", defaultValue="20161013") String date,
			@AuthenticationPrincipal final UserDetails user) {
		return opportunityCostsService.getOpportunityCostByDate(asset, cc, date, user);
	}

	@RequestMapping("/opportunitytoday")
	String getOpportunityToday(@RequestBody Asset asset,
			@RequestParam(value="cc", defaultValue="USD") String cc,
			@AuthenticationPrincipal final UserDetails user) {
		return opportunityCostsService.getOpportunityCostByToday(asset, cc, user);
	}
}

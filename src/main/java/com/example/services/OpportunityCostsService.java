package com.example.services;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.models.Asset;

public interface OpportunityCostsService {
	public String getCostByToday(@RequestBody Asset asset, String cc, final UserDetails user);
	public String getCostByDate(@RequestBody Asset asset, String cc, @PathVariable String date, final UserDetails user);
	public Map<String, String> getCostByTodayAll(@RequestBody Asset asset, String cc, final UserDetails user);
	public Map<String, String> getCostByDateAll(@RequestBody Asset asset, String cc, @PathVariable String date, final UserDetails user);
	public Map<String, String> getOpportunityByTodayAll(@RequestBody Asset asset, String cc, final UserDetails user);
	public Map<String, String> getOpportunityByDateAll(@RequestBody Asset asset, String cc, @PathVariable String date, final UserDetails user);
	public Map<String, Map<String, String>> getOpportunity(@RequestBody Asset asset, final UserDetails user);
	public Map<String, Map<String, String>> getOpportunityByDate(@RequestBody Asset asset, @PathVariable String date, final UserDetails user);
}

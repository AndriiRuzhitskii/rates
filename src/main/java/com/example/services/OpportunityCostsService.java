package com.example.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.models.Asset;

public interface OpportunityCostsService {
	public String getOpportunityCostByToday(@RequestBody Asset asset, String cc, final UserDetails user);
	public String getOpportunityCostByDate(@RequestBody Asset asset, String cc, @PathVariable String date, final UserDetails user);
}

package com.example.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.models.Account;
import com.example.models.Asset;

public interface AssetService {
	public List<Asset> getAll(final UserDetails user);
	public Asset create(@RequestBody Asset asset, final UserDetails user);
	public void delete(@PathVariable Long id, final UserDetails user);
	public Asset update(@PathVariable Long id, @RequestBody Asset asset, final UserDetails user);
	public Asset getById(@PathVariable Long id, final UserDetails user);
}

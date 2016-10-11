package com.example.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.models.Account;
import com.example.models.Asset;

public interface AssetService {
	public List<Asset> getAll();
	public Asset create(@RequestBody Asset asset);
	public void delete(@PathVariable Long id);
	public Asset update(@PathVariable Long id, @RequestBody Asset asset);
}

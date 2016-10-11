package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Account;
import com.example.models.Asset;
import com.example.services.AccountService;
import com.example.services.AssetService;

@RestController
@RequestMapping("/assets")
public class AssetController {

	@Autowired
	private AssetService service;

	@RequestMapping(method=RequestMethod.GET)
	public List<Asset> getAll() {
		return service.getAll();
	}

	@RequestMapping(method=RequestMethod.POST)
	public Asset create(@RequestBody Asset asset) {
		return service.create(asset);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
		
	}

	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public Asset update(@PathVariable Long id, @RequestBody Asset asset) {
		return service.update(id, asset);
	}

}

package com.example.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.DemoApplication;
import com.example.models.Account;
import com.example.models.AccountDao;
import com.example.models.Asset;
import com.example.models.AssetDao;

@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
	private AssetDao repository;
	
	@Autowired
	private AccountService accountService;
	
	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	@Override
	public List<Asset> getAll(final UserDetails user) {
		log.info("User " + user.getUsername());
		return (List<Asset>) repository.findAllByUser(user.getUsername());
	}

	@Override
	public Asset create(Asset asset, final UserDetails user) {
		log.info("User " + user.getUsername());
		Account account = accountService.findByUsername(user.getUsername());
		asset.setAccount(account);
		return repository.save(asset);
	}

	@Override
	public void delete(Long id, final UserDetails user) {
		log.info("User " + user.getUsername());
		Asset asset = repository.findOne(id);
		if(asset.getAccount().getUsername().equalsIgnoreCase(user.getUsername()))
			repository.delete(id);
	}

	@Override
	public Asset update(Long id, Asset asset, final UserDetails user) {
		log.info("User " + user.getUsername());
		Asset as = repository.findOne(id);
		as.setAmount(asset.getAmount());
		as.setCurrency(asset.getCurrency());
		as.setDate(asset.getDate());
		if(as.getAccount().getUsername().equalsIgnoreCase(user.getUsername()))
			repository.save(as);
		return as;
	}
}

package com.example.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DemoApplication;
import com.example.models.Account;
import com.example.models.AccountDao;

@Service
public class AssetServiceImpl implements AccountService {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);

	@Override
	public List<Account> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account create(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account update(Long id, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createAccountOnInit() {
		// TODO Auto-generated method stub
		
	}
	
}

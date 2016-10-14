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
public class AccountServiceImpl implements AccountService {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	
	@Autowired
	private AccountDao accountRepository;
	
	@Override
	public List<Account> getAll() {
		return (List<Account>) accountRepository.findAll();
	}

	@Override
	public Account create(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public void delete(Long id) {
		accountRepository.delete(id);
	}

	@Override
	public Account update(Long id, Account account) {
		Account acc = accountRepository.findOne(id);
		acc.setUsername(account.getUsername());
		acc.setPassword(account.getPassword());
		accountRepository.save(acc);
		return acc;
	}

	@Override
	public void createAccountOnInit() {
		log.info("createAccountOnInit()");
		accountRepository.save(new Account("user", "password"));
		log.info("accountRepository.findAll() = ");
		List<Account> list = (List<Account>) accountRepository.findAll();
		list.stream().forEach(System.out::println);
	}

	@Override
	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

}

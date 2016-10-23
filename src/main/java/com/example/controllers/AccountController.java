package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Account;
import com.example.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService service;

	@RequestMapping(method=RequestMethod.GET)
	public List<Account> getAll() {
		return service.getAll();
	}

	@RequestMapping(method=RequestMethod.POST)
	public Account create(@RequestBody Account account) {
		return service.create(account);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
		
	}

	@RequestMapping(method=RequestMethod.PUT, value="{id}")
	public Account update(@PathVariable Long id, @RequestBody Account account) {
		return service.update(id, account);
	}

}

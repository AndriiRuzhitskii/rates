package com.example.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.models.Account;

public interface AccountService {
	public List<Account> getAll();
	public Account create(@RequestBody Account account);
	public void delete(@PathVariable Long id);
	public Account update(@PathVariable Long id, @RequestBody Account account);
	public Account findByUsername(String username);
	public void createAccountOnInit();
}

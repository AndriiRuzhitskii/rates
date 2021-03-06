package com.example.models;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
public interface AccountDao extends CrudRepository<Account, Long> {
	
	public Account findByUsername(String username);
	
}

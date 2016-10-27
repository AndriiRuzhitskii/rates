package com.example.models;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public interface AssetDao extends CrudRepository<Asset,Long> {

	@Query("SELECT a FROM Asset a INNER JOIN a.account ac WHERE LOWER(ac.username)=LOWER(?1)")
	List<Asset> findAllByUser(String username);

}

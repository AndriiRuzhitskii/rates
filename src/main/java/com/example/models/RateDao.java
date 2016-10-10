package com.example.models;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
public interface RateDao extends CrudRepository<Rate, Long> {
	List<Rate> findByExchangedate(String exchangedate);
//	List<Rate> findByCc(String cc);
	
//    @Query("SELECT AVG(r.rate) FROM Rate r WHERE r.cc=?1")
//    Long getAverage(String cc);
//	
//    @Query("SELECT MAX(r) FROM Rate r WHERE r.cc=?1")
//    Rate getMax(String cc);
//	
//    @Query("SELECT MIN(r) FROM Rate r WHERE r.cc=?1")
//    Rate getMin(String cc);

}

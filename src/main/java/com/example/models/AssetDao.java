package com.example.models;

import org.junit.runner.RunWith;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public interface AssetDao extends CrudRepository<Currency,Long> {

}

package com.example.models;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PropertyDao extends CrudRepository<Property,Long> {
	@Query(value="SELECT * FROM Property p WHERE p.prop_name=?1", nativeQuery = true)
	public List<Property> getPropertiesByName(String name);
	
	@Query(value="SELECT * FROM Property p WHERE p.prop_name=?1", nativeQuery = true)
	public Property getPropertyByName(String name);
}

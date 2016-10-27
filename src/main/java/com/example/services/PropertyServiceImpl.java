package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Property;
import com.example.models.PropertyDao;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	private PropertyDao propertyDao;
	
	@Override
	public List<Property> getPropertiesByName(String name) {
		return propertyDao.getPropertiesByName(name);
	}
	
}

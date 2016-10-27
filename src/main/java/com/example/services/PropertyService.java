package com.example.services;

import java.util.List;

import com.example.models.Property;

public interface PropertyService {
	public List<Property> getPropertiesByName(String name);
}

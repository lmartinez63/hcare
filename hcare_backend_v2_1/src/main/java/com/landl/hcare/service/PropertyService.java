package com.landl.hcare.service;

import com.landl.hcare.entity.Property;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PropertyService {

    public Property save(Property employee);

    public List<Property> findAll();

    public Map<String, Object> getPropertiesMap();

    public Property findByPropertyCode(String propertyCode);

    public Optional<Property> findById(Long propertyId);

    public String getPropertyValue(String propertyCode);

    public Map getPropertiesGroupByModule() throws Exception;

}

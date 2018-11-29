package com.landl.hcare.service;

import com.landl.hcare.entity.Property;
import com.landl.hcare.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    PropertyRepository propertyRepository;

    public Property save(Property property){
        return propertyRepository.save(property);
    }

    public List<Property> findAll(){
        return propertyRepository.findAll();
    }

    public Map<String, Object> getPropertiesMap(){
        Map<String, Object> propertiesMap = new HashMap<String,Object>();
        for (Property property:findAll()) {
            propertiesMap.put(property.getPropertyCode(),property.getPropertyValue());
        }
        return propertiesMap;
    }

    public Property findByPropertyCode(String propertyCode){
        Property property = null;
        List<Property> propertyList = propertyRepository.findByPropertyCode(propertyCode);
        if (propertyList.size() > 0 ){
            property = propertyList.get(0);
        }
        return property;
    }

    public Optional<Property> findById(Long employeeId){
        return propertyRepository.findById(employeeId);
    }

    public String getPropertyValue(String propertyCode){
        Map<String, Object> propertiesMap = getPropertiesMap();
        if(propertiesMap.containsKey(propertyCode)) {
            return propertiesMap.get(propertyCode).toString();
        } else {
            return null;
        }
    }

}

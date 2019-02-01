package com.landl.hcare.service;

import com.landl.hcare.entity.Property;
import com.landl.hcare.repository.PropertyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.cache.CacheManager;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;
import java.util.*;

import static java.util.concurrent.TimeUnit.SECONDS;


@Service
@CacheDefaults(cacheName = "property")
public class PropertyServiceImpl implements PropertyService{

    @Autowired
    PropertyRepository propertyRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyServiceImpl.class);

    //create cache
    @Component
    public static class CachingSetup implements JCacheManagerCustomizer
    {
        @Override
        public void customize(CacheManager cacheManager)
        {
            cacheManager.createCache("property", new MutableConfiguration<>()
                    .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(SECONDS, 1000)))
                    .setStoreByValue(false)
                    .setStatisticsEnabled(true));
        }
    }

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

    @CacheResult
    public Property findByPropertyCode(String propertyCode){
        Property property = null;
        List<Property> propertyList = propertyRepository.findByPropertyCode(propertyCode);
        if (propertyList.size() > 0 ){
            property = propertyList.get(0);
        }
        return property;
    }

    public Map<String,Map<String,String>> getPropertiesGroupByModule() throws Exception{
        Map<String,Map<String,String>> moduleMap = new HashMap<String, Map<String,String>>();
        List<Object[]> returnData = propertyRepository.findPropertyGroupByModule();
        for(Object[] data:returnData){
            String module = (String)data[0];
            String propertyCode = (String)data[1];
            String propertyValue = (String)data[2];
            String labelValue = (String)data[3];
            //ModuleMap
            if(!moduleMap.containsKey(module)){
                moduleMap.put(module,new HashMap<String,String>());
            }
            //PropertyMap
            Map propertiesMap = (Map)moduleMap.get(module);
            if(!propertiesMap.containsKey(propertyCode)){
                propertiesMap.put(propertyCode,new HashMap<String,String>());
            }
            ((Map)propertiesMap.get(propertyCode)).put(propertyValue,labelValue);
        }
        return moduleMap;
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

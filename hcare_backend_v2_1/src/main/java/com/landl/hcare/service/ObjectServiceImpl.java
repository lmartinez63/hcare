package com.landl.hcare.service;

import com.landl.hcare.entity.Label;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


@Service
public class ObjectServiceImpl implements ObjectService{

    @Autowired
    LabelService labelService;

    public void transformObjectLabels(Object tObject) throws  Exception{
        if (tObject != null) {
            Class classInstance = tObject.getClass();
            Field[] attributes = classInstance.getDeclaredFields();
            for (Field field : attributes) {
                if (field.getName().startsWith("label")) {
                    String fieldValueName = field.getName().substring(5);
                    Method targetMethod = classInstance.getMethod("get" + fieldValueName, null);
                    Object returnObject = targetMethod.invoke(tObject, null);
                    if (returnObject != null) {
                        Label labelField = labelService.getByLabelCodeAndUserLanguage(returnObject.toString(), "GENERAL", fieldValueName.toUpperCase());
                        if (labelField != null)
                            PropertyUtils.setSimpleProperty(tObject, field.getName(), labelField.getLabelValueEsEs());
                    }
                }
            }
        }
    }

}

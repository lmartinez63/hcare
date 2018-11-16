package com.landl.hcare.common;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UtilityTools {

    public static String getFormatedValue(String value, Map<String,Object> dataSource) throws Exception{
        String formatedValue = "";
        formatedValue = value;
        Pattern p3 = Pattern.compile("\\{\\{(.*?)\\}\\}");
        Matcher m3 = p3.matcher(formatedValue);
        while (m3.find()) {
            String dinamicValue = m3.group(1);
            formatedValue = value.replace("{{"+dinamicValue+"}}",getReflectedValue(dinamicValue,dataSource).toString());
        }
        return  formatedValue;
    }
    public static Object getReflectedValue(String entityColumn, Map<String, Object>dataSource) throws Exception{
        //String entityColumnClean = entityColumn.substring(1).substring(0,entityColumn.length()-2);
        String className = entityColumn.substring(0,entityColumn.indexOf("."));
        String methodName = entityColumn.substring(entityColumn.indexOf(".")+1,entityColumn.length());
        Object objectSource = dataSource.get(className);
        Class classInstance = objectSource.getClass();
        Method targetMethod = classInstance.getMethod("get"+methodName.substring(0,1).toUpperCase() + methodName.substring(1),null);
        return targetMethod.invoke(objectSource,null);
    }

}

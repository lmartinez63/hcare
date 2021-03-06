package com.landl.hcare.common;

import com.landl.hcare.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UtilityTools {

    //TODO - must be stored in cache
    @Autowired
    PropertyService propertyService;

    public static String getFormatedValue(String value, Map<String,Object> dataSource) throws Exception{
        String formatedValue = "";
        formatedValue = value;
        if (!isEmpty(formatedValue)) {
            Pattern p3 = Pattern.compile("\\{\\{(.*?)\\}\\}");
            Matcher m3 = p3.matcher(formatedValue);
            while (m3.find()) {
                String dinamicValue = m3.group(1);
                String reflectedValue = dinamicValue;
                if(dinamicValue.startsWith(":")){
                    switch (dinamicValue){
                        case ":todayDateFormated":
                            //TODO set format in property
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            reflectedValue = format.format(new Date());
                        default:
                            break;
                    }
                }else{
                    Object objectValue = getReflectedValue(dinamicValue, dataSource);
                    if(objectValue != null){
                        reflectedValue = getReflectedValue(dinamicValue, dataSource).toString();
                    }
                    else{
                        reflectedValue = "null";
                    }
                }

                formatedValue = formatedValue.replace("{{" + dinamicValue + "}}", reflectedValue);
            }
        }
        return  formatedValue;
    }

    public static Object getObjectValue(String valueExpression, Map<String,Object> dataSource) throws Exception{
        Object returnObject = null;
        if (!isEmpty(valueExpression)) {
            Pattern p3 = Pattern.compile("\\{\\{(.*?)\\}\\}");
            Matcher m3 = p3.matcher(valueExpression);
            while (m3.find()) {
                String dinamicValue = m3.group(1);
                String reflectedValue = dinamicValue;
                if(dinamicValue.startsWith(":")){
                    switch (dinamicValue){
                        case ":todayDateFormated":
                            //TODO set format in property
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                            returnObject = format.format(new Date());
                        default:
                            break;
                    }
                }else{
                    try {
                        returnObject = getReflectedValue(dinamicValue, dataSource);
                    } catch(Exception e){
                        e.printStackTrace();
                        returnObject = null;
                    }
                }

            }
        }
        return returnObject;
    }

    public static Object getReflectedValue(String entityColumn, Map<String, Object>dataSource) throws Exception{
        //String entityColumnClean = entityColumn.substring(1).substring(0,entityColumn.length()-2);

        Object returnObject = null;
        if(entityColumn.contains(".")) {
            String className = entityColumn.substring(0,entityColumn.indexOf("."));
            Object objectSource = dataSource.get(className);
            Class classInstance = objectSource.getClass();
            String methodName = entityColumn.substring(entityColumn.indexOf(".") + 1, entityColumn.length());
            Method targetMethod = classInstance.getMethod("get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1), null);
            returnObject = targetMethod.invoke(objectSource, null);
        } else if (entityColumn.contains("_")){
            String className = entityColumn.substring(0,entityColumn.indexOf("_"));
            Object objectSource = dataSource.get(className);
            Class classInstance = objectSource.getClass();
            String subMethodName = entityColumn.substring(entityColumn.indexOf("_") + 1, entityColumn.length());
            String methodName = subMethodName.substring(0,subMethodName.indexOf("("));
            //TODO this needs to be changed to allow multiple parameters
            Method targetMethod = classInstance.getMethod(methodName, String.class);
            String argument = subMethodName.substring(subMethodName.indexOf("(")+1,subMethodName.indexOf(")"));
            returnObject = targetMethod.invoke(objectSource, argument);
        }
        return returnObject;
    }

    public static String isNull(String value){
        return (value == null) ? "" : value;
    }

    public static Object checkForNull(Object value){
        return (value == null) ? "" : value;
    }

    public static boolean isEmpty(String value){
        return (value == null || value.trim() == "") ? true : false;
    }

}

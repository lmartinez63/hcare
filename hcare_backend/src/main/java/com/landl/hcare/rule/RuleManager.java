package com.landl.hcare.rule;

import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.entity.MedicalAppointment;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

public class RuleManager {
    public static Boolean evaluateExpression(String expression,Map entityMap) throws Exception {
        Boolean result = true;
        String formattedValue = UtilityTools.getFormatedValue(expression, entityMap);

        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("JavaScript");
        //Examples
        //String formattedValue = "('abc' == 'xyz' && 'thy' == 'thy') || ('ujy' == 'ujy')";
        //String formattedValue = "'{{medicalAppointment.status}}' > '10'";
        return new Boolean(se.eval(formattedValue).toString());
    }

    public static void main(String[] args){

        try {
            Map<String, Object> entityMap = new HashMap<String, Object>();
            MedicalAppointment medicalAppointment = new MedicalAppointment();
            medicalAppointment.setStatus("20");
            entityMap.put("medicalAppointment",medicalAppointment);
            String expression = "'{{medicalAppointment.status}}' > '10'";
            evaluateExpression(expression, entityMap);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

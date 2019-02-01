package com.landl.hcare.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("customProcessSelector")
public class CustomProcessSelector {

    @Autowired
    RetrieveMedicalAppointmentInfo retrieveMedicalAppointmentInfo;

    public CustomProcess getCustomProcessClass(String customProcessName) throws Exception{
        CustomProcess customProcess = null;
        switch(customProcessName){
            case "RetrieveMedicalAppointmentInfo":
                customProcess = retrieveMedicalAppointmentInfo;
                break;
            default:
                    throw new Exception("Process "+customProcessName+"has not been found");
        }
        return customProcess;
    }
}

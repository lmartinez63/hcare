package com.landl.hcare.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("customProcessSelector")
public class CustomProcessSelector {

    @Autowired
    RetrieveMedicalAppointmentInfo retrieveMedicalAppointmentInfo;

    @Autowired
    RetrievePatientInfo retrievePatientInfo;

    @Autowired
    RetrieveMedicalAreaInfo retrieveMedicalAreaInfo;

    @Autowired
    RetrieveUserProfileInfo retrieveUserProfileInfo;

    @Autowired
    GetPatientByDocumentNumber getPatientByDocumentNumber;

    @Autowired
    SaveMedicalAppointment saveMedicalAppointment;

    @Autowired
    SavePatient savePatient;

    @Autowired
    RegisterMedicalAppointment registerMedicalAppointment;

    @Autowired
    RetrieveMedicalHistoryInfo retrieveMedicalHistoryInfo;


    public CustomProcess getCustomProcessClass(String customProcessName) throws Exception{
        CustomProcess customProcess = null;
        switch(customProcessName){
            case "RetrieveMedicalAppointmentInfo":
                customProcess = retrieveMedicalAppointmentInfo;
                break;
            case "RetrievePatientInfo":
                customProcess = retrievePatientInfo;
                break;
            case "RetrieveUserProfileInfo":
                customProcess = retrieveUserProfileInfo;
                break;
            case "RetrieveMedicalAreaInfo":
                customProcess = retrieveMedicalAreaInfo;
                break;
            case "RetrieveMedicalHistoryInfo":
                customProcess = retrieveMedicalHistoryInfo;
                break;
            case "GetPatientByDocumentNumber":
                customProcess = getPatientByDocumentNumber;
                break;
            case "RegisterMedicalAppointment":
                customProcess = registerMedicalAppointment;
                break;
            case "SaveMedicalAppointment":
                customProcess = saveMedicalAppointment;
                break;
            case "SavePatient":
                customProcess = savePatient;
                break;
            default:
                    throw new Exception("Process "+customProcessName+" has not been found");
        }
        return customProcess;
    }
}

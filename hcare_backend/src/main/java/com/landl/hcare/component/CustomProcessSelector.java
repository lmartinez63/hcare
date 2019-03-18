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
    SavePage savePage;

    @Autowired
    SaveSection saveSection;

    @Autowired
    SaveFieldDefinition saveFieldDefinition;

    @Autowired
    SaveLabel saveLabel;

    @Autowired
    RegisterMedicalAppointment registerMedicalAppointment;

    @Autowired
    RetrieveMedicalHistoryInfo retrieveMedicalHistoryInfo;

    @Autowired
    RetrievePageInfo retrievePageInfo;

    @Autowired
    RetrieveSectionInfo retrieveSectionInfo;

    @Autowired
    RetrieveFieldDefinitionInfo retrieveFieldDefinitionInfo;

    @Autowired
    RetrieveLabelInfo retrieveLabelInfo;

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
            case "RetrievePageInfo":
                customProcess = retrievePageInfo;
                break;
            case "RetrieveSectionInfo":
                customProcess = retrieveSectionInfo;
                break;
            case "RetrieveFieldDefinitionInfo":
                customProcess = retrieveFieldDefinitionInfo;
                break;
            case "RetrieveLabelInfo":
                customProcess = retrieveLabelInfo;
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
            case "SavePage":
                customProcess = savePage;
                break;
            case "SaveSection":
                customProcess = saveSection;
                break;
            case "SaveFieldDefinition":
                customProcess = saveFieldDefinition;
                break;
            case "SaveLabel":
                customProcess = saveLabel;
                break;
            default:
                    throw new Exception("Process "+customProcessName+" has not been found");
        }
        return customProcess;
    }
}

package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.MedicalHistory;
import com.landl.hcare.entity.Patient;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Component("retrieveMedicalHistoryInfo")
public class RetrieveMedicalHistoryInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{
    	
        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final MedicalHistory medicalHistoryRequest = mapper.convertValue(requestMap.get("medicalHistory"), MedicalHistory.class);
        MedicalHistory medicalHistory = medicalHistoryService.findById(medicalHistoryRequest.getHistoryCode());
        if (medicalHistory != null) {
	        if(medicalHistory.getHistoryCode() != null){
	            medicalHistory.setPatient(patientService.findByHistoryCode(medicalHistory.getHistoryCode()));
	        }
	        medicalHistory.setAttachmentList(attachmentService.findByEntityAndEntityId("medicalHistory", medicalHistory.getHistoryCode()));
        }
        addDataToResultMap("medicalHistory",medicalHistory);
    }
}
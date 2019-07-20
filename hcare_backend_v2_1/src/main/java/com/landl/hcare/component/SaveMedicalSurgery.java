package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.MedicalSurgery;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("saveMedicalSurgery")
public class SaveMedicalSurgery extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final MedicalSurgery medicalSurgeryRequest = mapper.convertValue(requestMap.get("medicalSurgery"), MedicalSurgery.class);
        MedicalSurgery medicalSurgery = null;
        if (medicalSurgeryRequest.getId() != null){
            medicalSurgery = medicalSurgeryService.findById(medicalSurgeryRequest.getId());
            copyNonNullProperties(medicalSurgeryRequest, medicalSurgery);
        } else {
            medicalSurgery = medicalSurgeryRequest;
        }
        addDataToResultMap("medicalSurgery",medicalSurgery);

    }



}

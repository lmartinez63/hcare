package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.MedicalArea;
import com.landl.hcare.entity.MedicalSurgery;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("retrieveMedicalSurgeryInfo")
public class RetrieveMedicalSurgeryInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        String s_medicalSurgeryId = (String)requestMap.get("medicalSurgeryId");
        MedicalSurgery medicalSurgery = null;
        if(s_medicalSurgeryId != null){
            Long l_medicalSurgeryId = Long.parseLong(s_medicalSurgeryId);
            medicalSurgery = medicalSurgeryService.findById(l_medicalSurgeryId);
        } else {
            String s_medicalAppintmentId = (String)requestMap.get("medicalAppintmentId");
            Long l_medicalAppintmentId = Long.parseLong(s_medicalAppintmentId);
            medicalSurgery = medicalSurgeryService.createMedicalSurgery(medicalAppointmentService.findById(l_medicalAppintmentId));
        }
        addDataToResultMap("medicalSurgery",medicalSurgery);
    }
}

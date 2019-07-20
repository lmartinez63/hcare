package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.MedicalAnalysis;
import com.landl.hcare.entity.MedicalSurgery;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("retrieveMedicalAnalysisInfo")
public class RetrieveMedicalAnalysisInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        String s_medicalAnalysisId = (String)requestMap.get("medicalAnalysisId");
        MedicalAnalysis medicalAnalysis = null;
        if(s_medicalAnalysisId != null){
            Long l_medicalAnalysisId = Long.parseLong(s_medicalAnalysisId);
            medicalAnalysis = medicalAnalysisService.findById(l_medicalAnalysisId);
        } else {
            String s_medicalSurgeryId = (String)requestMap.get("medicalSurgeryId");
            Long l_medicalSurgeryId = Long.parseLong(s_medicalSurgeryId);
            medicalAnalysis = medicalAnalysisService.createMedicalAnalysis(l_medicalSurgeryId);
        }
        addDataToResultMap("medicalAnalysis",medicalAnalysis);
    }
}

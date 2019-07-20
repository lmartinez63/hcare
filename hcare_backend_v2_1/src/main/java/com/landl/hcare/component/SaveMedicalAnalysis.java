package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.MedicalAnalysis;
import com.landl.hcare.entity.MedicalSurgery;
import com.landl.hcare.entity.type.MedicalAnalysisStatus;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("saveMedicalAnalysis")
public class SaveMedicalAnalysis extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final MedicalAnalysis medicalAnalysisRequest = mapper.convertValue(requestMap.get("medicalAnalysis"), MedicalAnalysis.class);
        MedicalAnalysis medicalAnalysis = null;
        Integer originalStatus = null;
        if (medicalAnalysisRequest.getId() != null){
            medicalAnalysis = medicalAnalysisService.findById(medicalAnalysisRequest.getId());
            originalStatus = medicalAnalysis.getStatus();
            copyNonNullProperties(medicalAnalysisRequest, medicalAnalysis);
        } else {
            medicalAnalysis = medicalAnalysisRequest;
        }
        if (medicalAnalysis.getId() == null && medicalAnalysis.getRequiredDate() != null) {
            medicalAnalysis.setStatus(MedicalAnalysisStatus.SCHEDULED);
        }
        //Actualizando Cita
        MedicalAnalysis medicalAnalysisSaved = medicalAnalysisService.save(medicalAnalysis);

        addDataToResultMap("medicalAnalysis",medicalAnalysisSaved);

    }



}

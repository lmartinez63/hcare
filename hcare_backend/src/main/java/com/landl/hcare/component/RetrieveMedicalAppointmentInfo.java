package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.MedicalAppointment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Component("retrieveMedicalAppointmentInfo")
public class RetrieveMedicalAppointmentInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final MedicalAppointment medicalAppointmentRequest = mapper.convertValue(requestMap.get("medicalAppointment"), MedicalAppointment.class);
        MedicalAppointment medicalAppointment = medicalAppointmentService.findById(medicalAppointmentRequest.getId()).get();
        if(medicalAppointment.getDocumentType() != null){
            medicalAppointment.setPatient(patientService.findByDocumentNumber(medicalAppointment.getDocumentNumber()));
        }
        medicalAppointment.setAttachmentList(attachmentService.findByEntityAndEntityId("medicalAppointment", medicalAppointment.getId()));
        addDataToResultMap("medicalAppointment",medicalAppointment);
    }



}

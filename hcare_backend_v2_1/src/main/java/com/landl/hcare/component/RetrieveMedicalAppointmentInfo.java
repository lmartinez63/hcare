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
        String s_medicalAppointmentId = (String)requestMap.get("medicalAppointmentId");
        MedicalAppointment medicalAppointment;
        if(s_medicalAppointmentId != null) {
            Long l_medicalAppointmentId = Long.parseLong(s_medicalAppointmentId);
            medicalAppointment = medicalAppointmentService.findById(l_medicalAppointmentId);
            if (medicalAppointment.getDocumentType() != null) {
                medicalAppointment.setPatient(patientService.findByDocumentNumber(medicalAppointment.getDocumentNumber()));
            }
            medicalAppointment.setAttachmentList(attachmentService.findByEntityAndEntityId("medicalAppointment", medicalAppointment.getId()));
        } else {
            medicalAppointment = medicalAppointmentService.createMedicalAppointment();
        }
        addDataToResultMap("medicalAppointment",medicalAppointment);
    }



}

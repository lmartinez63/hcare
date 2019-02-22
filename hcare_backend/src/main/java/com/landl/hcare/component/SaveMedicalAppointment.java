package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.MedicalHistory;
import com.landl.hcare.entity.Patient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("saveMedicalAppointment")
public class SaveMedicalAppointment extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final MedicalAppointment medicalAppointmentRequest = mapper.convertValue(requestMap.get("medicalAppointment"), MedicalAppointment.class);

        if (medicalAppointmentRequest.getId() == null) {
            throw new Exception("MedicalAppointment not found");
            //If is a new medical appointment send a reminder email
        }
        MedicalAppointment medicalAppointment= medicalAppointmentService.findById(medicalAppointmentRequest.getId());
        copyNonNullProperties(medicalAppointmentRequest, medicalAppointment);
        medicalAppointmentService.save(medicalAppointment);
        addDataToResultMap("medicalAppointment",medicalAppointment);
    }



}

package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.MedicalHistory;
import com.landl.hcare.entity.Patient;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("registerMedicalAppointment")
public class RegisterMedicalAppointment extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final MedicalAppointment medicalAppointment = mapper.convertValue(requestMap.get("medicalAppointment"), MedicalAppointment.class);

        if (medicalAppointment.getId() == null) {
            //If is a new medical appointment send a reminder email
            int emailPatientStatus = emailService.sendEmailToPatient(medicalAppointment);
            int emailDoctorStatus = emailService.sendEmailToDoctor(medicalAppointment);
            medicalAppointment.setStatus("5");
        }
        Patient patient = patientService.findByDocumentNumber(medicalAppointment.getDocumentNumber());
        //Create patient if doesn't exits
        if(patient == null){
            patient = patientService.createPatient(medicalAppointment);
        } else
        {
            patient = patientService.updatePatient(patient,medicalAppointment);
        }
        //Create history code if doesn't exits
        if (medicalAppointment.getStatus().compareTo("10")==0 && medicalAppointment.getHistoryCode() == null){
            //Create Medical History set incoming patient with historyCode
            MedicalHistory medicalHistory = medicalHistoryService.createMedicalHistory(patient);
            medicalAppointment.setHistoryCode(patient.getHistoryCode());
        }
        patient = patientService.save(patient);

        //Actualizando Cita
        MedicalAppointment medicalAppointmentSaved = medicalAppointmentService.save(medicalAppointment);
        medicalAppointmentSaved.setPatient(patient);
        medicalAppointmentSaved.setAttachmentList(attachmentService.findByEntityAndEntityId("medicalAppointment", medicalAppointmentSaved.getId()));

        addDataToResultMap("medicalAppointment",medicalAppointmentSaved);
    }



}

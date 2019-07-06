package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.MedicalHistory;
import com.landl.hcare.entity.Patient;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@Component("saveMedicalAppointment")
public class SaveMedicalAppointment extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final MedicalAppointment medicalAppointmentRequest = mapper.convertValue(requestMap.get("medicalAppointment"), MedicalAppointment.class);

        MedicalAppointment medicalAppointment = null;
        if (medicalAppointmentRequest.getId() != null){
            medicalAppointment = medicalAppointmentService.findById(medicalAppointmentRequest.getId());
            copyNonNullProperties(medicalAppointmentRequest, medicalAppointment);
        } else {
            medicalAppointment = medicalAppointmentRequest;
        }
        medicalAppointment.setAllergies(Arrays.toString(medicalAppointment.getAllergiesArray()));
        //Update patient fields
        /*
        Patient patient = patientService.findByDocumentNumber(medicalAppointment.getDocumentNumber());
        //Create patient if doesn't exits
        if(patient == null){
            patient = patientService.createPatient(medicalAppointment);
        } else
        {
            patient = patientService.updatePatient(patient,medicalAppointment);
        }
        patient = patientService.save(patient);
        */
        //Update Patient Information
        Patient patient = medicalAppointment.getPatient();
        patient = patientService.save(patient);
        medicalAppointment.setPatient(patient);

        //Send Notifications
        if (medicalAppointment.getId() == null) {
            //If is a new medical appointment send a reminder email
            int emailPatientStatus = emailService.sendEmailToPatient(medicalAppointment);
            int emailDoctorStatus = emailService.sendEmailToDoctor(medicalAppointment);
            medicalAppointment.setStatus("5");
        }

        //Create history code if doesn't exits
        if (medicalAppointment.getStatus().compareTo("10")==0){
            medicalAppointment.setDateAttention(new Date());
            if (medicalAppointment.getPatient().getHistoryCode() == null){
                //Create Medical History set incoming patient with historyCode
                MedicalHistory medicalHistory = medicalHistoryService.createMedicalHistory(medicalAppointment.getPatient());
            }
        }


        //Actualizando Cita
        MedicalAppointment medicalAppointmentSaved = medicalAppointmentService.save(medicalAppointment);
        //Not neccesary
        //medicalAppointmentSaved.setAttachmentList(attachmentService.findByEntityAndEntityId("medicalAppointment", medicalAppointmentSaved.getId()));

        addDataToResultMap("medicalAppointment",medicalAppointmentSaved);

    }



}

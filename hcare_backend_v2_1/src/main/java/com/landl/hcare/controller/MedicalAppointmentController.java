package com.landl.hcare.controller;

import com.landl.hcare.entity.*;
import com.landl.hcare.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MedicalAppointmentController {

    @Autowired
    private MedicalAppointmentService medicalAppointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private EmailTemplateService emailTemplateService;


    @Autowired
    private EmailService emailService;

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @GetMapping("/medicalAppointments")
    public List<MedicalAppointment> retrieveAllMedicalAppointments() throws Exception{
        return medicalAppointmentService.findAll();
    }
    /*
    @GetMapping("/medicalAppointmentsToday")
    public List<MedicalAppointment> retrieveTodayMedicalAppointments() throws Exception{
        return medicalAppointmentService.findByToday();
    }*/

    @PostMapping("/medicalAppointments")
    public MedicalAppointment saveMedicalAppointment(@Valid @RequestBody MedicalAppointment medicalAppointment) throws Exception{
        //If is a new medical appointment send a reminder email
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
        return medicalAppointmentSaved;
    }

    @GetMapping("/medicalAppointmentsByPatient/{documentNumber}")
    public List<MedicalAppointment> retrieveByPatient(@PathVariable String documentNumber) throws Exception{
        return medicalAppointmentService.findByDocumentNumber(documentNumber);
    }

}

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
    public List<MedicalAppointment> retrieveAllMedicalAppointments(){
        return medicalAppointmentService.findAll();
    }

    @GetMapping("/medicalAppointmentsToday")
    public List<MedicalAppointment> retrieveTodayMedicalAppointments(){
        return medicalAppointmentService.findByToday();
    }


    @GetMapping("/medicalAppointments/{medicalAppointmentId}")
    public MedicalAppointment retrieveByMedicalAppointmentId(@PathVariable Long medicalAppointmentId) throws Exception{
        MedicalAppointment medicalAppointment = medicalAppointmentService.findById(medicalAppointmentId).get();
        if(medicalAppointment.getDocumentType() != null){
            medicalAppointment.setPatient(patientService.findByDocumentNumber(medicalAppointment.getDocumentNumber()));
        }
        medicalAppointment.setAttachmentList(attachmentService.findByEntityAndEntityId("medicalAppointment", medicalAppointment.getId()));
        return medicalAppointment;
    }

    @PostMapping("/medicalAppointments")
    public MedicalAppointment saveMedicalAppointment(@Valid @RequestBody MedicalAppointment medicalAppointment)  throws Exception{
        //If is a new medical appointment send a reminder email
        if (medicalAppointment.getId() == null) {
            //If is a new medical appointment send a reminder email
            int emailPatientStatus = emailService.sendEmailToPatient(medicalAppointment);
            int emailDoctorStatus = emailService.sendEmailToDoctor(medicalAppointment);
            medicalAppointment.setStatus("5");
        }
        Patient patient = patientService.findByDocumentNumber(medicalAppointment.getDocumentNumber());
        //Create patient if doesn't exits
        if(patient == null || patient.getHistoryCode() == null){
            patient = patientService.createPatient(medicalAppointment);
            MedicalHistory medicalHistory = medicalHistoryService.createMedicalHistory(patient);
            medicalAppointment.setHistoryCode(patient.getHistoryCode());
        } else
        {
            patient = patientService.updatePatient(patient,medicalAppointment);
        }
        //Cita agendada
        MedicalAppointment medicalAppointmentSaved = medicalAppointmentService.save(medicalAppointment);

        medicalAppointmentSaved.setPatient(patient);
        medicalAppointmentSaved.setAttachmentList(attachmentService.findByEntityAndEntityId("medicalAppointment", medicalAppointmentSaved.getId()));
        return medicalAppointmentSaved;
    }

    @GetMapping("/medicalAppointmentsByHistoryCode/{historyCode}")
    public List<MedicalAppointment> retrieveByHistoryCode(@PathVariable Long historyCode) {
        return medicalAppointmentService.findByHistoryCode(historyCode);
    }

}

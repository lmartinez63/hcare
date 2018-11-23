package com.landl.hcare.controller;

import com.landl.hcare.entity.Email;
import com.landl.hcare.entity.EmailTemplate;
import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.Patient;
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

    @GetMapping("/medicalAppointments")
    public List<MedicalAppointment> retrieveAllMedicalAppointments(){
        return medicalAppointmentService.findAll();
    }

    @GetMapping("/medicalAppointmentsToday")
    public List<MedicalAppointment> retrieveTodayMedicalAppointments(){
        return medicalAppointmentService.findByToday();
    }


    @GetMapping("/medicalAppointments/{medicalAppointmentId}")
    public MedicalAppointment retrieveByMedicalHistoryId(@PathVariable Long medicalAppointmentId) {
        MedicalAppointment medicalAppointment = medicalAppointmentService.findById(medicalAppointmentId).get();
        if(medicalAppointment.getPatientId() != null){
            medicalAppointment.setPatient(patientService.findById(medicalAppointment.getPatientId()).get());
        }
        medicalAppointment.setAttachmentList(attachmentService.findByEntityAndEntityId("medicalAppointment", medicalAppointment.getId()));
        return medicalAppointment;
    }

    @PostMapping("/medicalAppointments")
    public MedicalAppointment saveMedicalAppointment(@Valid @RequestBody MedicalAppointment medicalAppointment)  throws Exception{
        //If is a new medical appointment send a reminder email
        if (medicalAppointment.getId() == null && medicalAppointment.getStatus().compareTo("0") == 0) {
            EmailTemplate emailTemplate = emailTemplateService.findByTemplateType("new_medicalAppointment");
            Email email = new Email();
            email.setStatus(0);
            email.setEmailTemplate(emailTemplate);
            //Get Data source
            Map<String,Object> dataSource = new HashMap<String, Object>();
            Patient patient = patientService.findById(medicalAppointment.getPatientId()).get();
            dataSource.put("Patient", patient);
            dataSource.put("MedicalAppointment", medicalAppointment);
            email.setDataSource(dataSource);
            emailService.buildEmailFromEmailTemplate(email);
            emailService.save(email);
        }
        MedicalAppointment medicalAppointmentSaved = medicalAppointmentService.save(medicalAppointment);
        if(medicalAppointmentSaved.getId() != null){
            medicalAppointmentSaved.setPatient(patientService.findById(medicalAppointmentSaved.getPatientId()).get());
            medicalAppointmentSaved.setAttachmentList(attachmentService.findByEntityAndEntityId("medicalAppointment", medicalAppointmentSaved.getId()));
        }
        return medicalAppointmentService.save(medicalAppointment);
    }

    @GetMapping("/medicalAppointmentsByPatientId/{patientId}")
    public List<MedicalAppointment> retrieveByPatientId(@PathVariable Long patientId) {
        return medicalAppointmentService.findByPatientId(patientId);
    }

}

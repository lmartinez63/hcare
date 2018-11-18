package com.landl.hcare.controller;

import com.landl.hcare.entity.Attachment;
import com.landl.hcare.entity.MedicalHistory;
import com.landl.hcare.exception.ResourceNotFoundException;
import com.landl.hcare.service.MedicalHistoryService;
import com.landl.hcare.service.PatientService;
import com.landl.hcare.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/medicalHistories")
    public List<MedicalHistory> retrieveAllMedicalHistories() {
        return medicalHistoryService.findAll();
    }

    @GetMapping("/medicalHistories/{medicalHistoryId}")
    public MedicalHistory retrieveByMedicalHistoryId(@PathVariable Long medicalHistoryId) {
        MedicalHistory medicalHistory = medicalHistoryService.findById(medicalHistoryId).get();
        return medicalHistory;
    }

    @PostMapping("/medicalHistories")
    public MedicalHistory saveMedicalHistory(@Valid @RequestBody MedicalHistory medicalHistory) {
        return medicalHistoryService.save(medicalHistory);
    }

    @GetMapping("/medicalHistory/{patientId}")
    public MedicalHistory retrieveByPatientId(@PathVariable Long patientId) {
        MedicalHistory medicalHistory = new MedicalHistory();
        if (medicalHistoryService.findByPatientId(patientId).size() > 0){
            medicalHistory =  medicalHistoryService.findByPatientId(patientId).get(0);
        }
        medicalHistory.setPatient(patientService.findById(patientId).get());
        medicalHistory.setAttachmentList(attachmentService.findByEntityAndEntityId("medicalHistory", medicalHistory.getId()));
        return medicalHistory;
    }

}

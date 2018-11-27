package com.landl.hcare.controller;

import com.landl.hcare.entity.MedicalHistory;
import com.landl.hcare.entity.Patient;
import com.landl.hcare.exception.ResourceNotFoundException;
import com.landl.hcare.service.MedicalHistoryService;
import com.landl.hcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @GetMapping("/patients")
    public List<Patient> retrieveAllPatients() {
        return patientService.findAll();
    }

    @GetMapping("/patients/{historyCode}")
    public Patient retrieveByHistoryCode(@PathVariable Long historyCode) {
        Patient patient = patientService.findById(historyCode).get();
        return patient;
    }

    @GetMapping("/retrievePatientByDocumentNumber/{documentNumber}")
    public Patient retrievePatientByDocumentNumber(@PathVariable String documentNumber) throws Exception {
        return patientService.findByDocumentNumber(documentNumber);
    }

    @PostMapping("/patients")
    public Patient savePatient(@Valid @RequestBody Patient patient) {
        Patient patientSaved = patientService.save(patient);
        //Create MedicalHistory with historyCode if doesn't exits
        Optional<MedicalHistory> optMedicalHistory = medicalHistoryService.findById(patient.getHistoryCode());
        if(!optMedicalHistory.isPresent()){
            MedicalHistory medicalHistory = medicalHistoryService.createMedicalHistory(patient);
        }
        return patientSaved;
    }

}

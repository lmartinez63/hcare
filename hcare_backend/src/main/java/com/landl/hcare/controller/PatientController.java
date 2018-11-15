package com.landl.hcare.controller;

import com.landl.hcare.entity.Patient;
import com.landl.hcare.exception.ResourceNotFoundException;
import com.landl.hcare.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public List<Patient> retrieveAllPatients() {
        return patientService.findAll();
    }

    @GetMapping("/patients/{patientId}")
    public Patient retrieveByPatientId(@PathVariable Long patientId) {
        Patient patient = patientService.findById(patientId).get();
        return patient;
    }

    @PostMapping("/patients")
    public Patient createPatient(@Valid @RequestBody Patient patient) {
        return patientService.save(patient);
    }

    @PutMapping("/patients/{patientId}")
    public Patient updatePatient(@PathVariable Long patientId,
                                   @Valid @RequestBody Patient patientRequest) {
        return patientService.findById(patientId)
                .map(patient -> {
                    patient.setDocumentNumber(patientRequest.getDocumentNumber());
                    patient.setDocumentType(patientRequest.getDocumentType());
                    patient.setFirstName(patientRequest.getFirstName());
                    patient.setLastName(patientRequest.getLastName());
                    patient.setEmailAddress(patientRequest.getEmailAddress());
                    patient.setPatientCode(patientRequest.getPatientCode());
                    return patientService.save(patient);
                }).orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + patientId));
    }

}

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
    public Patient savePatient(@Valid @RequestBody Patient patient) {
        return patientService.save(patient);
    }

}

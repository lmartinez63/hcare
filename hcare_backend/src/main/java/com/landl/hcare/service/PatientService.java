package com.landl.hcare.service;


import com.landl.hcare.entity.Patient;
import com.landl.hcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    public Patient save(Patient patient);

    public List<Patient> findAll();

    public Optional<Patient> findById(Long patientId);

}

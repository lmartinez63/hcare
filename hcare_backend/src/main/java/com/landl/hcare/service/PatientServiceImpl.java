package com.landl.hcare.service;

import com.landl.hcare.entity.Patient;
import com.landl.hcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    PatientRepository patientRepository;

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(Long patientId){
        return patientRepository.findById(patientId);
    }
}

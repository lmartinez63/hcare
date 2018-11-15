package com.landl.hcare.service;


import com.landl.hcare.entity.Patient;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PatientService {

    public Patient save(Patient patient);

    public List<Patient> findAll();

    public List<Patient> findByBirthday(Date birthday);

    public Optional<Patient> findById(Long patientId);

}

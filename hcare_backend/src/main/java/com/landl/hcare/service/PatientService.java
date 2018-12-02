package com.landl.hcare.service;


import com.landl.hcare.entity.Employee;
import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.Patient;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PatientService {

    public Patient save(Patient patient);

    public List<Patient> findAll();

    public List<Patient> findByBirthday(Date birthday);

    public Patient findByDocumentNumber(String documentNumber) throws Exception;

    public Optional<Patient> findById(Long patientId);

    public Patient findByHistoryCode(Long historyCode) throws Exception;

    public Patient createPatient(MedicalAppointment medicalAppointment) throws Exception;
    public Patient updatePatient(Patient patient, MedicalAppointment medicalAppointment) throws Exception;

}

package com.landl.hcare.service;

import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.Patient;
import com.landl.hcare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public List<Patient> findByBirthday(Date birthday){
        return patientRepository.findByBirthday(birthday);
    };
    public Patient findByDocumentNumber(String documentNumber) throws Exception {
        Patient patient = null;
        List<Patient> patientList  =  patientRepository.findByDocumentNumber(documentNumber);
        if( patientList.size() > 0 ){
            patient = patientList.get(0);
        }
        return patient;
    };

    public Optional<Patient> findById(Long patientId){
        return patientRepository.findById(patientId);
    }

    public Patient createPatient(MedicalAppointment medicalAppointment) throws Exception{
        Patient patient = new Patient();
        patient.setDocumentNumber(medicalAppointment.getDocumentNumber());
        patient.setDocumentType(medicalAppointment.getDocumentType());
        patient.setLastName(medicalAppointment.getLastName());
        patient.setFirstName(medicalAppointment.getFirstName());
        patient.setCelPhoneNumber(medicalAppointment.getCelPhoneNumber());
        patient.setEmailAddress(medicalAppointment.getEmailAddress());
        return save(patient);
    }

    public Patient updatePatient(Patient patient, MedicalAppointment medicalAppointment) throws Exception{
        patient.setDocumentNumber(medicalAppointment.getDocumentNumber());
        patient.setDocumentType(medicalAppointment.getDocumentType());
        patient.setLastName(medicalAppointment.getLastName());
        patient.setFirstName(medicalAppointment.getFirstName());
        patient.setCelPhoneNumber(medicalAppointment.getCelPhoneNumber());
        patient.setEmailAddress(medicalAppointment.getEmailAddress());
        return save(patient);
    }
}

package com.landl.hcare.service;

import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.repository.MedicalAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalAppointmentServiceImpl implements MedicalAppointmentService {

    @Autowired
    MedicalAppointmentRepository medicalAppointmentRepository;

    public MedicalAppointment save(MedicalAppointment medicalAppointment){
        return medicalAppointmentRepository.save(medicalAppointment);
    }

    public List<MedicalAppointment> findAll(){
        return medicalAppointmentRepository.findAll();
    }

    public Optional<MedicalAppointment> findById(Long medicalAppointmentId){
        return medicalAppointmentRepository.findById(medicalAppointmentId);
    }
}

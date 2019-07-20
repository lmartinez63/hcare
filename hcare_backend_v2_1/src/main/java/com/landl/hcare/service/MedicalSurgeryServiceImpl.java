package com.landl.hcare.service;

import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.MedicalSurgery;
import com.landl.hcare.entity.type.MedicalSurgeryStatus;
import com.landl.hcare.repository.MedicalSurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalSurgeryServiceImpl implements MedicalSurgeryService {

    @Autowired
    MedicalSurgeryRepository medicalSurgeryRepository;

    public MedicalSurgery save(MedicalSurgery medicalAppointment) throws Exception{
        return medicalSurgeryRepository.save(medicalAppointment);
    }

    public List<MedicalSurgery> findAll() throws Exception{
        return medicalSurgeryRepository.findAll();
    }

    public MedicalSurgery findById(Long medicalAppointmentId) throws Exception{
        return medicalSurgeryRepository.findById(medicalAppointmentId).get();
    }
    public MedicalSurgery createMedicalSurgery(MedicalAppointment medicalAppointment) throws  Exception{
        MedicalSurgery medicalSurgery = new MedicalSurgery();
        medicalSurgery.setStatus(MedicalSurgeryStatus.PENDING);
        medicalSurgery.setMedicalAppointment(medicalAppointment);
        return medicalSurgery;
    }
}

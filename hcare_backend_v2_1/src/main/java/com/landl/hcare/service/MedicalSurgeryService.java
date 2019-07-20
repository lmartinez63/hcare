package com.landl.hcare.service;


import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.MedicalSurgery;

import java.util.List;

public interface MedicalSurgeryService {

    public MedicalSurgery save(MedicalSurgery medicalSurgery) throws Exception;

    public List<MedicalSurgery> findAll() throws Exception;

    public MedicalSurgery findById(Long medicalSurgeryId) throws Exception;

    public MedicalSurgery createMedicalSurgery(MedicalAppointment medicalAppointment) throws  Exception;

}

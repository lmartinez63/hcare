package com.landl.hcare.service;


import com.landl.hcare.entity.MedicalAppointment;

import java.util.List;
import java.util.Optional;

public interface MedicalAppointmentService {

    public MedicalAppointment save(MedicalAppointment medicalAppointment);

    public List<MedicalAppointment> findAll();

    public Optional<MedicalAppointment> findById(Long medicalAppointmentId);

    public List<MedicalAppointment> findByHistoryCode(Long historyCode);

    public List<MedicalAppointment> findByToday();

}

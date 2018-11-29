package com.landl.hcare.service;


import com.landl.hcare.entity.MedicalAppointment;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MedicalAppointmentService {

    public MedicalAppointment save(MedicalAppointment medicalAppointment) throws Exception;

    public List<MedicalAppointment> findAll() throws Exception;

    public Optional<MedicalAppointment> findById(Long medicalAppointmentId) throws Exception;

    public List<MedicalAppointment> findByHistoryCode(Long historyCode) throws Exception;

    public List<MedicalAppointment> findByDocumentNumber(String documentNumber) throws Exception;

    public List<MedicalAppointment> findByToday() throws Exception;

    public List<MedicalAppointment> findByDateAppointment(Date date) throws Exception;

    public List<MedicalAppointment> findByDateAppointmentAndDoctorId(Date date,Long doctorId) throws Exception;

    public Long countByDateAppointment(Date date) throws Exception;

    public Long countByStatusAndDateAppointment(String status, Date date) throws Exception;

}

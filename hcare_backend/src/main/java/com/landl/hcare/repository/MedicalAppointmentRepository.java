package com.landl.hcare.repository;

import com.landl.hcare.entity.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long>{
    List<MedicalAppointment> findByPatientId(Long patientId);
    List<MedicalAppointment> findByDateAppointmentBetween(Date firstDate,Date secondDate);
}

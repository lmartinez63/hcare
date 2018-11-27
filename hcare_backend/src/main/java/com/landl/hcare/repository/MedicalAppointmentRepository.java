package com.landl.hcare.repository;

import com.landl.hcare.entity.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long>{
    List<MedicalAppointment> findByHistoryCode(Long historyCode);
    List<MedicalAppointment> findByDateAppointmentBetweenOrderByDateAppointmentAsc(Date firstDate,Date secondDate);
    List<MedicalAppointment> findByDoctorIdAndDateAppointmentBetweenOrderByDateAppointmentAsc(Long doctorId, Date firstDate,Date secondDate);
}

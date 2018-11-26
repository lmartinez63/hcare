package com.landl.hcare.service;

import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.repository.MedicalAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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

    public List<MedicalAppointment> findByHistoryCode(Long historyCode){
        return medicalAppointmentRepository.findByHistoryCode(historyCode);
    }

    public List<MedicalAppointment> findByToday(){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        today = cal.getTime();

        Date tomorrow = new Date();
        cal.setTime(today);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        tomorrow = cal.getTime();
        return medicalAppointmentRepository.findByDateAppointmentBetween(today,tomorrow);
    };
}

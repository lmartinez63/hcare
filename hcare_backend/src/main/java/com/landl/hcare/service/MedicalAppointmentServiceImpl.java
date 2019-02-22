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

    public MedicalAppointment save(MedicalAppointment medicalAppointment) throws Exception{
        return medicalAppointmentRepository.save(medicalAppointment);
    }

    public List<MedicalAppointment> findAll() throws Exception{
        return medicalAppointmentRepository.findAll();
    }

    public MedicalAppointment findById(Long medicalAppointmentId) throws Exception{
        return medicalAppointmentRepository.findById(medicalAppointmentId).get();
    }

    public List<MedicalAppointment> findByHistoryCode(Long historyCode) throws Exception{
        return medicalAppointmentRepository.findByHistoryCode(historyCode);
    }

    public List<MedicalAppointment> findByDocumentNumber(String documentNumber) throws Exception{
        return medicalAppointmentRepository.findByDocumentNumber(documentNumber);
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
        return medicalAppointmentRepository.findByDateAppointmentBetweenOrderByDateAppointmentAsc(today,tomorrow);
    };

    public List<MedicalAppointment> findByDateAppointment(Date date){
        Date targetDate = date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(targetDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        targetDate = cal.getTime();

        Date dayAfterTargetDate = new Date();
        cal.setTime(targetDate);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dayAfterTargetDate = cal.getTime();
        return medicalAppointmentRepository.findByDateAppointmentBetweenOrderByDateAppointmentAsc(targetDate,dayAfterTargetDate);
    };

    public List<MedicalAppointment> findByDateAppointmentAndDoctorId(Date date,Long doctorId){
        Date targetDate = date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(targetDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        targetDate = cal.getTime();

        Date dayAfterTargetDate = new Date();
        cal.setTime(targetDate);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dayAfterTargetDate = cal.getTime();

        return medicalAppointmentRepository.findByDoctorIdAndDateAppointmentBetweenOrderByDateAppointmentAsc(doctorId,targetDate,dayAfterTargetDate);
    };

    public Long countByDateAppointment(Date date){
        Date targetDate = date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(targetDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        targetDate = cal.getTime();

        Date dayAfterTargetDate = new Date();
        cal.setTime(targetDate);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dayAfterTargetDate = cal.getTime();
        return medicalAppointmentRepository.countByDateAppointmentBetweenOrderByDateAppointmentAsc(targetDate,dayAfterTargetDate);
    }

    public Long countByStatusAndDateAppointment(String status, Date date){
        Date targetDate = date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(targetDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        targetDate = cal.getTime();

        Date dayAfterTargetDate = new Date();
        cal.setTime(targetDate);
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dayAfterTargetDate = cal.getTime();
        return medicalAppointmentRepository.countByStatusAndDateAppointmentBetweenOrderByDateAppointmentAsc(status,targetDate,dayAfterTargetDate);
    }
}

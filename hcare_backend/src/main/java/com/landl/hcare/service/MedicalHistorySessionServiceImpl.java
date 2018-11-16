package com.landl.hcare.service;

import com.landl.hcare.entity.MedicalHistorySession;
import com.landl.hcare.repository.MedicalHistorySessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistorySessionServiceImpl implements MedicalHistorySessionService {

    @Autowired
    MedicalHistorySessionRepository medicalHistorySessionRepository;

    public MedicalHistorySession save(MedicalHistorySession medicalHistorySession){
        return medicalHistorySessionRepository.save(medicalHistorySession);
    }

    public List<MedicalHistorySession> findAll(){
        return medicalHistorySessionRepository.findAll();
    }

    public Optional<MedicalHistorySession> findById(Long medicalHistorySessionId){
        return medicalHistorySessionRepository.findById(medicalHistorySessionId);
    }
}

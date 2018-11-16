package com.landl.hcare.service;


import com.landl.hcare.entity.MedicalHistorySession;

import java.util.List;
import java.util.Optional;

public interface MedicalHistorySessionService {

    public MedicalHistorySession save(MedicalHistorySession medicalHistorySession);

    public List<MedicalHistorySession> findAll();

    public Optional<MedicalHistorySession> findById(Long medicalHistorySessionId);

}

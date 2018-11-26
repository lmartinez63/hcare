package com.landl.hcare.service;


import com.landl.hcare.entity.MedicalHistory;
import com.landl.hcare.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface MedicalHistoryService {

    public MedicalHistory save(MedicalHistory medicalHistory);

    public List<MedicalHistory> findAll();

    public Optional<MedicalHistory> findById(Long historyCode);

    public MedicalHistory createMedicalHistory(Patient patient);

}

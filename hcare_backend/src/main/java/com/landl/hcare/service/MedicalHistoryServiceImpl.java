package com.landl.hcare.service;

import com.landl.hcare.entity.MedicalHistory;
import com.landl.hcare.entity.Patient;
import com.landl.hcare.repository.MedicalHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistory save(MedicalHistory medicalHistory){
        return medicalHistoryRepository.save(medicalHistory);
    }

    public List<MedicalHistory> findAll(){
        return medicalHistoryRepository.findAll();
    }

    public Optional<MedicalHistory> findById(Long historyCode){
        return medicalHistoryRepository.findById(historyCode);
    }

    public MedicalHistory createMedicalHistory(Patient patient){
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setHistoryCode(patient.getHistoryCode());
        medicalHistory.setStatus("true");
        return save(medicalHistory);
    }
}

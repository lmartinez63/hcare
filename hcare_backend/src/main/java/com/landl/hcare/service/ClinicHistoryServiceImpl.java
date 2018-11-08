package com.landl.hcare.service;

import com.landl.hcare.entity.ClinicHistory;
import com.landl.hcare.repository.ClinicHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicHistoryServiceImpl implements ClinicHistoryService{

    @Autowired
    ClinicHistoryRepository clinicHistoryRepository;

    public ClinicHistory save(ClinicHistory clinicHistory){
        return clinicHistoryRepository.save(clinicHistory);
    }

    public List<ClinicHistory> findAll(){
        return clinicHistoryRepository.findAll();
    }

    public Optional<ClinicHistory> findById(Long clinicHistoryId){
        return clinicHistoryRepository.findById(clinicHistoryId);
    }
}

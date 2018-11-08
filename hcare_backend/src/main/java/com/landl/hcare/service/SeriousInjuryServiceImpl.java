package com.landl.hcare.service;

import com.landl.hcare.entity.SeriousInjury;
import com.landl.hcare.repository.SeriousInjuryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeriousInjuryServiceImpl implements SeriousInjuryService{

    @Autowired
    SeriousInjuryRepository seriousInjuryRepository;

    public SeriousInjury save(SeriousInjury seriousInjury){
        return seriousInjuryRepository.save(seriousInjury);
    }

    public List<SeriousInjury> findAll(){
        return seriousInjuryRepository.findAll();
    }

    public Optional<SeriousInjury> findById(Long seriousInjuryId){
        return seriousInjuryRepository.findById(seriousInjuryId);
    }
}

package com.landl.hcare.service;


import com.landl.hcare.entity.SeriousInjury;

import java.util.List;
import java.util.Optional;

public interface SeriousInjuryService {

    public SeriousInjury save(SeriousInjury seriousInjury);

    public List<SeriousInjury> findAll();

    public Optional<SeriousInjury> findById(Long seriousInjuryId);

}

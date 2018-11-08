package com.landl.hcare.service;


import com.landl.hcare.entity.ClinicHistory;

import java.util.List;
import java.util.Optional;

public interface ClinicHistoryService {

    public ClinicHistory save(ClinicHistory clinicHistory);

    public List<ClinicHistory> findAll();

    public Optional<ClinicHistory> findById(Long clinicHistoryId);

}

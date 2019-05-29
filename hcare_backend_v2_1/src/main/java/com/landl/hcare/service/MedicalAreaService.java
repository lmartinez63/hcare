package com.landl.hcare.service;


import com.landl.hcare.entity.MedicalArea;

import java.util.List;
import java.util.Optional;

public interface MedicalAreaService {

    public MedicalArea save(MedicalArea medicalArea);

    public List<MedicalArea> findAll();

    public Optional<MedicalArea> findById(Long medicalAreaId);

}

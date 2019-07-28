package com.landl.hcare.service;

import com.landl.hcare.entity.SurgeryArea;

import java.util.List;

public interface SurgeryAreaService {

    public SurgeryArea save(SurgeryArea surgeryArea) throws Exception;

    public List<SurgeryArea> findAll() throws Exception;

    public SurgeryArea findById(Long surgeryAreaId) throws Exception;

    public SurgeryArea createSurgeryArea() throws  Exception;

}

package com.landl.hcare.service;
import com.landl.hcare.entity.SurgeryArea;
import com.landl.hcare.entity.type.GeneralStatus;
import com.landl.hcare.repository.SurgeryAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurgeryAreaServiceImpl implements SurgeryAreaService {

    @Autowired
    SurgeryAreaRepository surgeryAreaRepository;

    public SurgeryArea save(SurgeryArea surgeryArea) throws Exception{
        return surgeryAreaRepository.save(surgeryArea);
    }

    public List<SurgeryArea> findAll() throws Exception{
        return surgeryAreaRepository.findAll();
    }

    public SurgeryArea findById(Long surgeryAreaId) throws Exception{
        return surgeryAreaRepository.findById(surgeryAreaId).get();
    }
    public SurgeryArea createSurgeryArea() throws  Exception{
        SurgeryArea surgeryArea = new SurgeryArea();
        surgeryArea.setActivationStatus(false);
        return surgeryArea;
    }
}

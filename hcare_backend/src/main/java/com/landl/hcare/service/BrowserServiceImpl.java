package com.landl.hcare.service;

import com.landl.hcare.entity.DataTableResult;
import com.landl.hcare.entity.MedAppHeaderView;
import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.repository.MedAppHeaderViewRepository;
import com.landl.hcare.repository.MedicalAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BrowserServiceImpl implements BrowserService {

    @Autowired
    MedAppHeaderViewRepository medAppHeaderViewRepository;

    public List<MedAppHeaderView> findAllMedAppHeaderView() throws Exception {
        return medAppHeaderViewRepository.findAll();
    }

    public DataTableResult buildDataTableObject(String browseType) throws Exception {
        DataTableResult dataTableResult = new DataTableResult();
        List result = null;
        switch (browseType){
            case "allMedAppHeaderView":
                result = medAppHeaderViewRepository.findByLmatLanguageAndLmasLanguageOrderByMaDateAppointmentDesc("ES_ES","ES_ES");
                break;
        }
        dataTableResult.setData(result);
        dataTableResult.setDraw(1);

        return dataTableResult;
    }

}

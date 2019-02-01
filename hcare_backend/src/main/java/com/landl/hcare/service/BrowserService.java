package com.landl.hcare.service;


import com.landl.hcare.entity.DataTableResult;
import com.landl.hcare.entity.MedAppHeaderView;
import com.landl.hcare.entity.MedicalAppointment;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BrowserService {

    public List<MedAppHeaderView> findAllMedAppHeaderView() throws Exception;
    public DataTableResult buildDataTableObject(String browseType) throws Exception;

}

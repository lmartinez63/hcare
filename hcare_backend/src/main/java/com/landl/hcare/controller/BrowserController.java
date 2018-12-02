package com.landl.hcare.controller;

import com.landl.hcare.entity.MedAppHeaderView;
import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.MedicalHistory;
import com.landl.hcare.entity.Patient;
import com.landl.hcare.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BrowserController {

    @Autowired
    private BrowserService browserService;


    @GetMapping("/medicalAppointmentsHeaderView")
    public List<MedAppHeaderView> retrieveAllMedicalAppointmentsHeaderView() throws Exception{
        return browserService.findAllMedAppHeaderView();
    }

}

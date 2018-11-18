package com.landl.hcare.controller;

import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.service.MedicalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MedicalAppointmentController {

    @Autowired
    private MedicalAppointmentService medicalAppointmentService;

    @GetMapping("/medicalAppointments")
    public List<MedicalAppointment> retrieveAllMedicalAppointments() {
        return medicalAppointmentService.findAll();
    }

    @GetMapping("/medicalAppointments/{medicalAppointmentId}")
    public MedicalAppointment retrieveByMedicalHistoryId(@PathVariable Long medicalAppointmentId) {
        MedicalAppointment medicalAppointment = medicalAppointmentService.findById(medicalAppointmentId).get();
        return medicalAppointment;
    }

    @PostMapping("/medicalAppointments")
    public MedicalAppointment saveMedicalAppointment(@Valid @RequestBody MedicalAppointment medicalAppointment) {
        return medicalAppointmentService.save(medicalAppointment);
    }

}

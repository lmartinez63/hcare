package com.landl.hcare.controller;

import com.landl.hcare.entity.MedicalHistorySession;
import com.landl.hcare.service.MedicalHistorySessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MedicalHistorySessionController {

    @Autowired
    private MedicalHistorySessionService medicalHistorySessionService;

    @GetMapping("/medicalHistorySessions")
    public List<MedicalHistorySession> retrieveAllMedicalHistorySessions() {
        return medicalHistorySessionService.findAll();
    }

    @GetMapping("/medicalHistorySessions/{medicalHistorySessionId}")
    public MedicalHistorySession retrieveByMedicalHistoryId(@PathVariable Long medicalHistorySessionId) {
        MedicalHistorySession medicalHistorySession = medicalHistorySessionService.findById(medicalHistorySessionId).get();
        return medicalHistorySession;
    }

    @PostMapping("/medicalHistorySessions")
    public MedicalHistorySession saveMedicalHistory(@Valid @RequestBody MedicalHistorySession medicalHistorySession) {
        return medicalHistorySessionService.save(medicalHistorySession);
    }

}

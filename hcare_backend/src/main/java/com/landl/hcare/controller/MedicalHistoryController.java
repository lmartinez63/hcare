package com.landl.hcare.controller;

import com.landl.hcare.entity.MedicalHistory;
import com.landl.hcare.exception.ResourceNotFoundException;
import com.landl.hcare.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @GetMapping("/medicalHistories")
    public List<MedicalHistory> retrieveAllMedicalHistories() {
        return medicalHistoryService.findAll();
    }

    @GetMapping("/medicalHistories/{medicalHistoryId}")
    public MedicalHistory retrieveByMedicalHistoryId(@PathVariable Long medicalHistoryId) {
        MedicalHistory medicalHistory = medicalHistoryService.findById(medicalHistoryId).get();
        return medicalHistory;
    }

    @PostMapping("/medicalHistories")
    public MedicalHistory saveMedicalHistory(@Valid @RequestBody MedicalHistory medicalHistory) {
        return medicalHistoryService.save(medicalHistory);
    }

}

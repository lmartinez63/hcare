package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="medical_history_session")
public class MedicalHistorySession extends AuditModel {
    @Id
    @GeneratedValue(generator = "med_his_ses_generator")
    @SequenceGenerator(
            name = "med_his_ses_generator",
            sequenceName = "med_his_ses_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="medical_history_id")
    private Long medicalHistoryId;

    @Column(name="session_date")
    private Date sessionDate;

    @Column(name="visit_reason")
    private String visitReason;

    @Column(name="dianostic")
    private String diagnostic;

    public MedicalHistorySession() {
    }

    public Long getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(Long medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }
}
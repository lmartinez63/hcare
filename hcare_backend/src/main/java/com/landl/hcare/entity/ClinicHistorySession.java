package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="clinic_history_session")
public class ClinicHistorySession extends AuditModel {
    @Id
    @GeneratedValue(generator = "cli_his_ses_generator")
    @SequenceGenerator(
            name = "cli_his_ses_generator",
            sequenceName = "cli_his_ses_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="clinic_history_id")
    private Long clinicHistoryId;

    @Column(name="session_date")
    private Date sessionDate;

    @Column(name="visit_reason")
    private String visitReason;

    @Column(name="dianostic")
    private String diagnostic;

    public ClinicHistorySession() {
    }

    public Long getClinicHistoryId() {
        return clinicHistoryId;
    }

    public void setClinicHistoryId(Long clinicHistoryId) {
        this.clinicHistoryId = clinicHistoryId;
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
package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="medical_appointment")
public class MedicalAppointment  extends AuditModel {
    @Id
    @GeneratedValue(generator = "med_app_generator")
    @SequenceGenerator(
            name = "med_app_generator",
            sequenceName = "med_app_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="patient_id")
    private Long patientId;

    @Column(name="patient_code")
    private String patientCode;

    @Column(name="date_appointment")
    private Date dateAppointment;

    @Column(name="status")
    private String status;

    @Column(name="medical_area_id")
    private Long medicalAreaId;

    @Column(name="doctor_id")
    private Long doctorId;

    @Column(name="visit_reason")
    private String visitReason;

    @Column(name="dianostic")
    private String diagnostic;

    @Transient
    private Patient patient;

    @Transient
    private List<Attachment> attachmentList;

    public MedicalAppointment() {
    }

    public Date getDateAppointment() {
        return dateAppointment;
    }

    public void setDateAppointment(Date dateAppointment) {
        this.dateAppointment = dateAppointment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getMedicalAreaId() {
        return medicalAreaId;
    }

    public void setMedicalAreaId(Long medicalAreaId) {
        this.medicalAreaId = medicalAreaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }
}

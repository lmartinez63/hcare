package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;
import com.landl.hcare.common.UtilityTools;

import javax.persistence.*;
import java.text.SimpleDateFormat;
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
            allocationSize = 1,
            initialValue = 1000
    )
    private Long id;

    @Column(name="history_code")
    private Long historyCode;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Column(name="document_type")
    private String documentType;

    @Column(name="document_number")
    private String documentNumber;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="cel_phone_number")
    private String celPhoneNumber;

    @Column(name="date_appointment")
    private Date dateAppointment;

    @Transient
    private String dateAppointmentDateFormatted;

    @Transient
    private String dateAppointmentTimeFormatted;

    @Column(name="status")
    private String status;

    @Column(name="medical_area_id")
    private Long medicalAreaId;

    @Column(name="doctor_id")
    private Long doctorId;

    @Column(name="visit_reason")
    private String visitReason;

    @Column(name="notes",length = 4000, nullable = true)
    private String notes;

    @Column(name="dianostic", nullable = true)
    private String diagnostic;

    @Column(name="preferential_diagnostic", nullable = true)
    private String preferentialDiagnostic;

    @Column(name="medical_appointment_type")
    private String medicalAppointmentType;

    @Transient
    private Patient patient;

    @Transient
    private List<Attachment> attachmentList;

    public MedicalAppointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCelPhoneNumber() {
        return celPhoneNumber;
    }

    public void setCelPhoneNumber(String celPhoneNumber) {
        this.celPhoneNumber = celPhoneNumber;
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

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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

    public String getMedicalAppointmentType() {
        return medicalAppointmentType;
    }

    public void setMedicalAppointmentType(String medicalAppointmentType) {
        this.medicalAppointmentType = medicalAppointmentType;
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

    public Long getHistoryCode() {
        return historyCode;
    }

    public void setHistoryCode(Long historyCode) {
        this.historyCode = historyCode;
    }

    public String getFullName() {
        return UtilityTools.isNull(this.firstName) + " " + UtilityTools.isNull(this.lastName);
    }

    public String getDateAppointmentDateFormatted() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(dateAppointment);
    }

    public String getDateAppointmentTimeFormatted() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        return format.format(dateAppointment);
    }

    public String getPreferentialDiagnostic() {
        return preferentialDiagnostic;
    }

    public void setPreferentialDiagnostic(String preferentialDiagnostic) {
        this.preferentialDiagnostic = preferentialDiagnostic;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

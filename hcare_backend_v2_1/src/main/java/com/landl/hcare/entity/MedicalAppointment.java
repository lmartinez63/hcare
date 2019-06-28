package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;
import com.landl.hcare.common.UtilityTools;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Audited
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

    @Column(name="date_appointment")
    private Date dateAppointment;

    @Transient
    @NotAudited
    private String dateAppointmentDateFormatted;

    @Transient
    @NotAudited
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
    private Integer preferentialDiagnostic;

    @Column(name="medical_appointment_type")
    private Integer medicalAppointmentType;

    //Map one to one association between Person and Address
    @OneToOne(cascade = CascadeType.MERGE)
    private Patient patient;

    @Transient
    @NotAudited
    private List<Attachment> attachmentList;

    //Format for directories frontEnd
    @Transient
    @NotAudited
    private List<Map> files;

    public MedicalAppointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getMedicalAppointmentType() {
        return medicalAppointmentType;
    }

    public void setMedicalAppointmentType(Integer medicalAppointmentType) {
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

    public String getDateAppointmentDateFormatted() {
        if (dateAppointment != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(dateAppointment);
        }
        return null;
    }

    public String getDateAppointmentTimeFormatted() {
        if (dateAppointment != null) {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
            return format.format(dateAppointment);
        }
        return null;
    }

    public Integer getPreferentialDiagnostic() {
        return preferentialDiagnostic;
    }

    public void setPreferentialDiagnostic(Integer preferentialDiagnostic) {
        this.preferentialDiagnostic = preferentialDiagnostic;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Map> getFiles() {
        return files;
    }

    public void setFiles(List<Map> files) {
        this.files = files;
    }
}

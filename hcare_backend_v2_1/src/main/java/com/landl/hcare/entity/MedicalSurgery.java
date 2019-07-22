package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Audited
@Table(name="medical_surgery")
public class MedicalSurgery extends AuditModel {
    @Id
    @GeneratedValue(generator = "medical_surgery_generator")
    @SequenceGenerator(
            name = "medical_surgery_generator",
            sequenceName = "medical_surgery_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    private Long id;

    @Column(name="surgery_type")
    @Audited
    private Integer surgeryType;

    @Column(name="status")
    @Audited
    private Integer status;

    @Column(name="required_datetime")
    @Audited
    private Date requiredDatetime;

    @Column(name="surgery_start_datetime")
    @Audited
    private Date surgeryStartDatetime;

    @Column(name="surgery_end_datetime")
    @Audited
    private Date surgeryEndDatetime;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="surgery_area_id")
    private SurgeryArea surgeryArea;

    @OneToMany
    @JoinColumn(name = "medical_surgery_id")
    private List<SurgeryDoctor> surgeryDoctors;

    @OneToMany
    @JoinColumn(name = "medical_surgery_id")
    private List<SurgeryNurse> surgeryNurses;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name="medical_appointment_id")
    private MedicalAppointment medicalAppointment;

    @OneToMany
    @JoinColumn(name = "medical_surgery_id")
    private List<MedicalAnalysis> medicalAnalysisList;

    @Transient
    private Patient patient;

    //Format for directories frontEnd
    @Transient
    @NotAudited
    private List<Map> files;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSurgeryType() {
        return surgeryType;
    }

    public void setSurgeryType(Integer surgeryType) {
        this.surgeryType = surgeryType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRequiredDatetime() {
        return requiredDatetime;
    }

    public void setRequiredDatetime(Date requiredDatetime) {
        this.requiredDatetime = requiredDatetime;
    }

    public Date getSurgeryStartDatetime() {
        return surgeryStartDatetime;
    }

    public void setSurgeryStartDatetime(Date surgeryStartDatetime) {
        this.surgeryStartDatetime = surgeryStartDatetime;
    }

    public Date getSurgeryEndDatetime() {
        return surgeryEndDatetime;
    }

    public void setSurgeryEndDatetime(Date surgeryEndDatetime) {
        this.surgeryEndDatetime = surgeryEndDatetime;
    }

    public SurgeryArea getSurgeryArea() {
        return surgeryArea;
    }

    public void setSurgeryArea(SurgeryArea surgeryArea) {
        this.surgeryArea = surgeryArea;
    }

    public List<SurgeryDoctor> getSurgeryDoctors() {
        return surgeryDoctors;
    }

    public void setSurgeryDoctors(List<SurgeryDoctor> surgeryDoctors) {
        this.surgeryDoctors = surgeryDoctors;
    }

    public List<SurgeryNurse> getSurgeryNurses() {
        return surgeryNurses;
    }

    public void setSurgeryNurses(List<SurgeryNurse> surgeryNurses) {
        this.surgeryNurses = surgeryNurses;
    }

    public MedicalAppointment getMedicalAppointment() {
        return medicalAppointment;
    }

    public void setMedicalAppointment(MedicalAppointment medicalAppointment) {
        this.medicalAppointment = medicalAppointment;
    }

    public List<MedicalAnalysis> getMedicalAnalysisList() {
        return medicalAnalysisList;
    }

    public void setMedicalAnalysisList(List<MedicalAnalysis> medicalAnalysisList) {
        this.medicalAnalysisList = medicalAnalysisList;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Map> getFiles() {
        return files;
    }

    public void setFiles(List<Map> files) {
        this.files = files;
    }
}

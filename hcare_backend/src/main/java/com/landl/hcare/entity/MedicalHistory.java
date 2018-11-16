package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name="medical_history")
public class MedicalHistory extends AuditModel {
    @Id
    @GeneratedValue(generator = "medical_history_generator")
    @SequenceGenerator(
            name = "medical_history_generator",
            sequenceName = "medical_history_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="clinic_history_code")
    private String clinicHistoryCode;

    @Column(name="patientId")
    private Long patientId;

    @Column(name="fileNumber")
    private String fileNumber;

    @Column(name="smoke")
    private String smoke;

    @Column(name="packages_cigarettes_pday")
    private Integer packagesCigarettesPday;

    @Column(name="hep_b_result")
    private String hepBResult;

    @Column(name="hep_b_year")
    private String hepBYear;

    @Column(name="hep_c_result")
    private String hepCResult;

    @Column(name="hep_c_year")
    private String hepCYear;

    @Column(name="vih_result")
    private String vihResult;

    @Column(name="vih_year")
    private String vihYear;

    @Column(name="use_any_subtance")
    private String useAnySubtance;

    @Column(name="use_any_subtance_freq")
    private String use_any_subtance_freq;

    public MedicalHistory() {
    }


    public String getClinicHistoryCode() {
        return clinicHistoryCode;
    }

    public void setClinicHistoryCode(String clinicHistoryCode) {
        this.clinicHistoryCode = clinicHistoryCode;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public Integer getPackagesCigarettesPday() {
        return packagesCigarettesPday;
    }

    public void setPackagesCigarettesPday(Integer packagesCigarettesPday) {
        this.packagesCigarettesPday = packagesCigarettesPday;
    }

    public String getHepBResult() {
        return hepBResult;
    }

    public void setHepBResult(String hepBResult) {
        this.hepBResult = hepBResult;
    }

    public String getHepBYear() {
        return hepBYear;
    }

    public void setHepBYear(String hepBYear) {
        this.hepBYear = hepBYear;
    }

    public String getHepCResult() {
        return hepCResult;
    }

    public void setHepCResult(String hepCResult) {
        this.hepCResult = hepCResult;
    }

    public String getHepCYear() {
        return hepCYear;
    }

    public void setHepCYear(String hepCYear) {
        this.hepCYear = hepCYear;
    }

    public String getVihResult() {
        return vihResult;
    }

    public void setVihResult(String vihResult) {
        this.vihResult = vihResult;
    }

    public String getVihYear() {
        return vihYear;
    }

    public void setVihYear(String vihYear) {
        this.vihYear = vihYear;
    }

    public String getUseAnySubtance() {
        return useAnySubtance;
    }

    public void setUseAnySubtance(String useAnySubtance) {
        this.useAnySubtance = useAnySubtance;
    }

    public String getUse_any_subtance_freq() {
        return use_any_subtance_freq;
    }

    public void setUse_any_subtance_freq(String use_any_subtance_freq) {
        this.use_any_subtance_freq = use_any_subtance_freq;
    }
}
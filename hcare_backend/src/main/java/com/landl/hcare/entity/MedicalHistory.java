package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="medical_history")
public class MedicalHistory extends AuditModel {
    @Id
    @GeneratedValue(generator = "history_code_generator")
    @SequenceGenerator(
            name = "history_code_generator",
            sequenceName = "history_code_sequence",
            initialValue = 2500,
            allocationSize = 1
    )
    private Long historyCode;

    @Column(name="old_history_code")
    private String oldHistoryCode;


    @Column(name="file_number")
    private String fileNumber;

    @Column(name="status")
    private String status;

    @Column(name="history_activity")
    private Boolean historyActivity;

    //record
    @Column(name="previous_surgeries")
    private String previousSurgeries;

    @Column(name="previous_diseases")
    private String previousDiseases;

    @Column(name="hospitalizations")
    private String hospitalizations;

    @Column(name="medication_allergies")
    private String medicationAllergies;

    @Column(name="smoke")
    private String smoke;

    @Column(name="packages_cigarettes_pday")
    private Integer packagesCigarettesPday;

    @Column(name="drink")
    private String drink;

    @Column(name="how_often_drink")
    private String howOftenDrink;

    @Column(name="preferential_diet")
    private String preferentialDiet;

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
    private String useAnySubtanceFreq;

    @Transient
    private Patient patient;

    @Transient
    private List<Attachment> attachmentList;

    public MedicalHistory() {
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

    public String getUseAnySubtanceFreq() {
        return useAnySubtanceFreq;
    }

    public void setUseAnySubtanceFreq(String useAnySubtanceFreq) {
        this.useAnySubtanceFreq = useAnySubtanceFreq;
    }

    public String getPreviousSurgeries() {
        return previousSurgeries;
    }

    public void setPreviousSurgeries(String previousSurgeries) {
        this.previousSurgeries = previousSurgeries;
    }

    public String getPreviousDiseases() {
        return previousDiseases;
    }

    public void setPreviousDiseases(String previousDiseases) {
        this.previousDiseases = previousDiseases;
    }

    public String getHospitalizations() {
        return hospitalizations;
    }

    public void setHospitalizations(String hospitalizations) {
        this.hospitalizations = hospitalizations;
    }

    public String getMedicationAllergies() {
        return medicationAllergies;
    }

    public void setMedicationAllergies(String medicationAllergies) {
        this.medicationAllergies = medicationAllergies;
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

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getHowOftenDrink() {
        return howOftenDrink;
    }

    public void setHowOftenDrink(String howOftenDrink) {
        this.howOftenDrink = howOftenDrink;
    }

    public String getPreferentialDiet() {
        return preferentialDiet;
    }

    public void setPreferentialDiet(String preferentialDiet) {
        this.preferentialDiet = preferentialDiet;
    }

    public Long getHistoryCode() {
        return historyCode;
    }

    public void setHistoryCode(Long historyCode) {
        this.historyCode = historyCode;
    }

    public String getOldHistoryCode() {
        return oldHistoryCode;
    }

    public void setOldHistoryCode(String oldHistoryCode) {
        this.oldHistoryCode = oldHistoryCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getHistoryActivity() {
        return historyActivity;
    }

    public void setHistoryActivity(Boolean historyActivity) {
        this.historyActivity = historyActivity;
    }
}
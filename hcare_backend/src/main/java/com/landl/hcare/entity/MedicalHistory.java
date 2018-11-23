package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.List;

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

    @Column(name="medical_history_code")
    private String medicalHistoryCode;

    @Column(name="patient_id")
    private Long patientId;

    @Column(name="patient_code")
    private String patientCode;

    @Column(name="fileNumber")
    private String fileNumber;

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
    private String use_any_subtance_freq;

    @Transient
    private Patient patient;

    @Transient
    private List<Attachment> attachmentList;

    public MedicalHistory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicalHistoryCode() {
        return medicalHistoryCode;
    }

    public void setMedicalHistoryCode(String medicalHistoryCode) {
        this.medicalHistoryCode = medicalHistoryCode;
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

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
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
}
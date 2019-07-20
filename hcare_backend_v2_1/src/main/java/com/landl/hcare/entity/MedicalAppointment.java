package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;
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

    @Column(name="date_attention")
    private Date dateAttention;

    @Transient
    @NotAudited
    private String dateAppointmentDateFormatted;

    @Transient
    @NotAudited
    private String dateAppointmentTimeFormatted;

    @Column(name="status")
    private String status;

    @Transient
    private String labelStatus;

    @Column(name="medical_area_id")
    private Long medicalAreaId;

    @Column(name="doctor_id")
    private Long doctorId;

    @Column(name="notes",length = 4000, nullable = true)
    private String notes;


    @Column(name="medical_appointment_type")
    private Integer medicalAppointmentType;

    @Column(name="parent_medical_appointment_id")
    private Long parentMedicalAppointmentId;

    //Anamnesis
    @Column(name="main_symptoms", nullable = true,length = 4000)
    private String mainSymptoms;

    @Column(name="allergies", nullable = true,length = 4000)
    private String allergies;

    @Transient
    private Integer[] allergiesArray;

    @Column(name="pathological_background", nullable = true,length = 4000)
    private String pathologicalBackground;

    @Column(name="other_pathological_background", nullable = true,length = 4000)
    private String otherPathologicalBackground;

    @Column(name="visit_reason", nullable = true)
    private String visitReason;

    @Column(name="sickness_time_quantity")
    private Integer sicknessTimeQuantity;

    @Column(name="sickness_time_type")
    private Integer sicknessTimeType;

    @Column(name="chronological_story", nullable = true)
    private String chronologicalStory;

    @Column(name="biological_functions", nullable = true)
    private String biologicalFunctions;

    @Column(name="background", nullable = true,length = 4000)
    private String background;


    //Examen Clinico
    @Column(name="general_clinic_exam", nullable = true,length = 4000)
    private String generalClinicExam;

    @Column(name="fc", nullable = true)
    private Integer fc;

    @Column(name="fr", nullable = true)
    private Integer fr;

    @Column(name="t", nullable = true)
    private Integer t;

    @Column(name="pa", nullable = true)
    private String pa;

    @Column(name="weight", nullable = true)
    private Integer weight;

    @Column(name="height", nullable = true)
    private Integer height;

    @Column(name="general_status", nullable = true)
    private String generalStatus;

    @Column(name="hydration_status", nullable = true)
    private String hydrationStatus;

    @Column(name="nutrition_status", nullable = true)
    private String nutritionStatus;

    @Column(name="consciousness_state", nullable = true)
    private String consciousnessState;

    @Column(name="imc", nullable = true)
    private String imc;

    @Column(name="tcsc", nullable = true)
    private String tcsc;

    @Column(name="head", nullable = true)
    private String head;

    @Column(name="mouth", nullable = true)
    private String mouth;

    @Column(name="neck", nullable = true)
    private String neck;

    @Column(name="thorax", nullable = true)
    private String thorax;

    @Column(name="heart", nullable = true)
    private String heart;

    @Column(name="lungs", nullable = true)
    private String lungs;

    @Column(name="abdomen", nullable = true)
    private String abdomen;

    @Column(name="urinary_genito", nullable = true)
    private String urinaryGenito;

    @Column(name="extremities", nullable = true)
    private String extremities;

    @Column(name="peripheral_pulses", nullable = true)
    private String peripheralPulses;

    @Column(name="neurological", nullable = true)
    private String neurological;

    @Column(name="regional_clinic_exam", nullable = true, length = 4000)
    private String regionalClinicExam;

    //Diagnostic
    @Column(name="presumptive_coherent", nullable = true, length = 4000)
    private String presumptiveCoherent;

    @Column(name="definitive_coherent", nullable = true)
    private String definitiveCoherent;

    @Column(name="preferential_diagnostic", nullable = true)
    private Integer preferentialDiagnostic;

    //WorkPlan
    //@Column(name="presumptive_coherent", nullable = true,length = 4000)
    //private String presumptiveCoherent;

    //Map one to one association between Person and Address
    @OneToOne(cascade = CascadeType.MERGE)
    private Patient patient;


    @OneToMany
    @JoinColumn(name = "medical_appointment_id")
    private List<PageButton> pageButtons;

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

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getGeneralClinicExam() {
        return generalClinicExam;
    }

    public void setGeneralClinicExam(String generalClinicExam) {
        this.generalClinicExam = generalClinicExam;
    }

    public Integer getFc() {
        return fc;
    }

    public void setFc(Integer fc) {
        this.fc = fc;
    }

    public Integer getFr() {
        return fr;
    }

    public void setFr(Integer fr) {
        this.fr = fr;
    }

    public Integer getT() {
        return t;
    }

    public void setT(Integer t) {
        this.t = t;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getGeneralStatus() {
        return generalStatus;
    }

    public void setGeneralStatus(String generalStatus) {
        this.generalStatus = generalStatus;
    }

    public String getHydrationStatus() {
        return hydrationStatus;
    }

    public void setHydrationStatus(String hydrationStatus) {
        this.hydrationStatus = hydrationStatus;
    }

    public String getNutritionStatus() {
        return nutritionStatus;
    }

    public void setNutritionStatus(String nutritionStatus) {
        this.nutritionStatus = nutritionStatus;
    }

    public String getConsciousnessState() {
        return consciousnessState;
    }

    public void setConsciousnessState(String consciousnessState) {
        this.consciousnessState = consciousnessState;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getTcsc() {
        return tcsc;
    }

    public void setTcsc(String tcsc) {
        this.tcsc = tcsc;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getMouth() {
        return mouth;
    }

    public void setMouth(String mouth) {
        this.mouth = mouth;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getThorax() {
        return thorax;
    }

    public void setThorax(String thorax) {
        this.thorax = thorax;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getLungs() {
        return lungs;
    }

    public void setLungs(String lungs) {
        this.lungs = lungs;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getUrinaryGenito() {
        return urinaryGenito;
    }

    public void setUrinaryGenito(String urinaryGenito) {
        this.urinaryGenito = urinaryGenito;
    }

    public String getExtremities() {
        return extremities;
    }

    public void setExtremities(String extremities) {
        this.extremities = extremities;
    }

    public String getPeripheralPulses() {
        return peripheralPulses;
    }

    public void setPeripheralPulses(String peripheralPulses) {
        this.peripheralPulses = peripheralPulses;
    }

    public String getNeurological() {
        return neurological;
    }

    public void setNeurological(String neurological) {
        this.neurological = neurological;
    }

    public String getRegionalClinicExam() {
        return regionalClinicExam;
    }

    public void setRegionalClinicExam(String regionalClinicExam) {
        this.regionalClinicExam = regionalClinicExam;
    }

    public String getPresumptiveCoherent() {
        return presumptiveCoherent;
    }

    public void setPresumptiveCoherent(String presumptiveCoherent) {
        this.presumptiveCoherent = presumptiveCoherent;
    }

    public String getDefinitiveCoherent() {
        return definitiveCoherent;
    }

    public void setDefinitiveCoherent(String definitiveCoherent) {
        this.definitiveCoherent = definitiveCoherent;
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

    public String getMainSymptoms() {
        return mainSymptoms;
    }

    public void setMainSymptoms(String mainSymptoms) {
        this.mainSymptoms = mainSymptoms;
    }

    public String getPathologicalBackground() {
        return pathologicalBackground;
    }

    public void setPathologicalBackground(String pathologicalBackground) {
        this.pathologicalBackground = pathologicalBackground;
    }

    public String getOtherPathologicalBackground() {
        return otherPathologicalBackground;
    }

    public void setOtherPathologicalBackground(String otherPathologicalBackground) {
        this.otherPathologicalBackground = otherPathologicalBackground;
    }

    public Integer getSicknessTimeQuantity() {
        return sicknessTimeQuantity;
    }

    public void setSicknessTimeQuantity(Integer sicknessTimeQuantity) {
        this.sicknessTimeQuantity = sicknessTimeQuantity;
    }

    public Integer getSicknessTimeType() {
        return sicknessTimeType;
    }

    public void setSicknessTimeType(Integer sicknessTimeType) {
        this.sicknessTimeType = sicknessTimeType;
    }

    public String getChronologicalStory() {
        return chronologicalStory;
    }

    public void setChronologicalStory(String chronologicalStory) {
        this.chronologicalStory = chronologicalStory;
    }

    public String getBiologicalFunctions() {
        return biologicalFunctions;
    }

    public void setBiologicalFunctions(String biologicalFunctions) {
        this.biologicalFunctions = biologicalFunctions;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public Integer[] getAllergiesArray() {
        return allergiesArray;
    }

    public void setAllergiesArray(Integer[] allergiesArray) {
        this.allergiesArray = allergiesArray;
    }

    public String getLabelStatus() {
        return labelStatus;
    }

    public void setLabelStatus(String labelStatus) {
        this.labelStatus = labelStatus;
    }

    public Date getDateAttention() {
        return dateAttention;
    }

    public void setDateAttention(Date dateAttention) {
        this.dateAttention = dateAttention;
    }

    public Long getParentMedicalAppointmentId() {
        return parentMedicalAppointmentId;
    }

    public void setParentMedicalAppointmentId(Long parentMedicalAppointmentId) {
        this.parentMedicalAppointmentId = parentMedicalAppointmentId;
    }
}

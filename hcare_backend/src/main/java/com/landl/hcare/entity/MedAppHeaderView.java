package com.landl.hcare.entity;

import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.model.AuditModel;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Immutable
@Table(name="med_app_header_view")
public class MedAppHeaderView{
    @Id
    private Long maId;

    @Column(name="ma_history_code")
    private Long maHistoryCode;

    @Column(name="ma_full_name")
    private String maFullName;

    @Column(name="ma_date_appointment")
    private Date maDateAppointment;

    @Column(name="ma_med_app_type")
    private String maMedAppType;

    @Column(name="ma_status")
    private String maStatus;


    @Transient
    private String maDateAppointmentDateFormatted;

    @Transient
    private String maDateAppointmentTimeFormatted;

    @Column(name="ma_doctor_id")
    private Long maDoctorId;

    @Column(name="em_full_name")
    private String emFullName;

    @Column(name="lmat_language")
    private String lmatLanguage;

    @Column(name="lmas_language")
    private String lmasLanguage;


    public MedAppHeaderView() {
    }

    public Long getMaId() {
        return maId;
    }

    public void setMaId(Long maId) {
        this.maId = maId;
    }

    public Long getMaHistoryCode() {
        return maHistoryCode;
    }

    public void setMaHistoryCode(Long maHistoryCode) {
        this.maHistoryCode = maHistoryCode;
    }

    public String getMaFullName() {
        return maFullName;
    }

    public void setMaFullName(String maFullName) {
        this.maFullName = maFullName;
    }

    public Date getMaDateAppointment() {
        return maDateAppointment;
    }

    public void setMaDateAppointment(Date maDateAppointment) {
        this.maDateAppointment = maDateAppointment;
    }

    public Long getMaDoctorId() {
        return maDoctorId;
    }

    public void setMaDoctorId(Long maDoctorId) {
        this.maDoctorId = maDoctorId;
    }

    public String getEmFullName() {
        return emFullName;
    }

    public void setEmFullName(String emFullName) {
        this.emFullName = emFullName;
    }

    public String getMaDateAppointmentDateFormatted() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(maDateAppointment);
    }

    public String getMaDateAppointmentTimeFormatted() {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        return format.format(maDateAppointment);
    }

    public String getMaMedAppType() {
        return maMedAppType;
    }

    public void setMaMedAppType(String maMedAppType) {
        this.maMedAppType = maMedAppType;
    }

    public String getMaStatus() {
        return maStatus;
    }

    public void setMaStatus(String maStatus) {
        this.maStatus = maStatus;
    }

    public String getLmatLanguage() {
        return lmatLanguage;
    }

    public void setLmatLanguage(String lmatLanguage) {
        this.lmatLanguage = lmatLanguage;
    }

    public String getLmasLanguage() {
        return lmasLanguage;
    }

    public void setLmasLanguage(String lmasLanguage) {
        this.lmasLanguage = lmasLanguage;
    }
}

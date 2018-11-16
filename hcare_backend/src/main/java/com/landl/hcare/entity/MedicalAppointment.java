package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name="date_appointment")
    private Date dateAppointment;

    @Column(name="status")
    private String status;

    @Column(name="medical_area_id")
    private Long medicalAreaId;

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
}

package com.landl.hcare.entity;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
public class SurgeryDoctor {

    @Id
    @Audited
    private Long doctorId;

    @Column(name="doctorType")
    @Audited
    private Integer doctorType;

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(Integer doctorType) {
        this.doctorType = doctorType;
    }
}
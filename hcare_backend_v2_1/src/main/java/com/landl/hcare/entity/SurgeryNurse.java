package com.landl.hcare.entity;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Audited
public class SurgeryNurse {

    @Id
    @Audited
    private Long nurseId;

    @Column(name="nurseType")
    @Audited
    private Integer nurseType;

    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public Integer getNurseType() {
        return nurseType;
    }

    public void setNurseType(Integer nurseType) {
        this.nurseType = nurseType;
    }
}
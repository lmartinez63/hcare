package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="medical_area")
public class MedicalArea  extends AuditModel {
    @Id
    @GeneratedValue(generator = "med_area_generator")
    @SequenceGenerator(
            name = "med_area_generator",
            sequenceName = "med_area_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="area_name")
    private String areaName;

    public MedicalArea() {
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}

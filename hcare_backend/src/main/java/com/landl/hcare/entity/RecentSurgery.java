package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="recent_surgery")
public class RecentSurgery extends AuditModel {
    @Id
    @GeneratedValue(generator = "recent_surgery_generator")
    @SequenceGenerator(
            name = "recent_surgery_generator",
            sequenceName = "recent_surgery_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="clinic_history_id")
    private Long clinicHistoryId;

    @Column(name="date_surgery")
    private Date dateSurgery;

    @Column(name="surgery")
    private String surgery;

    public RecentSurgery() {
    }

    public Long getClinicHistoryId() {
        return clinicHistoryId;
    }

    public void setClinicHistoryId(Long clinicHistoryId) {
        this.clinicHistoryId = clinicHistoryId;
    }

    public Date getDateSurgery() {
        return dateSurgery;
    }

    public void setDateSurgery(Date dateSurgery) {
        this.dateSurgery = dateSurgery;
    }

    public String getSurgery() {
        return surgery;
    }

    public void setSurgery(String surgery) {
        this.surgery = surgery;
    }
}
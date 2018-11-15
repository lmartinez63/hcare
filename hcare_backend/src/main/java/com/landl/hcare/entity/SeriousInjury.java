package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="serious_injuries")
public class SeriousInjury extends AuditModel {
    @Id
    @GeneratedValue(generator = "serious_injuries_generator")
    @SequenceGenerator(
            name = "serious_injuries_generator",
            sequenceName = "serious_injuries_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name="medical_history_id")
    private Long medicalHistoryId;

    @Column(name="date_surgery")
    private Date dateSurgery;

    @Column(name="surgery")
    private String surgery;

    public SeriousInjury() {
    }

    public Long getmedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setmedicalHistoryId(Long medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
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
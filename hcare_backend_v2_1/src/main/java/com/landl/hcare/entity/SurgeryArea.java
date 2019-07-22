package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
@Audited
public class SurgeryArea extends AuditModel {

    @Id
    @Audited
    private Long id;

    @Column(name="surgery_area_code")
    @Audited
    private String surgeryAreaCode;

    @Column(name="status")
    @Audited
    private Integer status;

    @Column(name="monday_start_time_surgeries")
    @Audited
    private LocalTime mondayStartTimeSurgeries;

    @Column(name="monday_end_time_surgeries")
    @Audited
    private LocalTime mondayEndTimeSurgeries;

    @Column(name="tuesday_start_time_surgeries")
    @Audited
    private LocalTime tuesdayStartTimeSurgeries;

    @Column(name="tuesday_end_time_surgeries")
    @Audited
    private LocalTime tuesdayEndTimeSurgeries;

    @Column(name="wednesday_start_time_surgeries")
    @Audited
    private LocalTime wednesdayStartTimeSurgeries;

    @Column(name="wednesday_end_time_surgeries")
    @Audited
    private LocalTime wednesdayEndTimeSurgeries;

    @Column(name="thursday_start_time_surgeries")
    @Audited
    private LocalTime thursdayStartTimeSurgeries;

    @Column(name="thursday_end_time_surgeries")
    @Audited
    private LocalTime thursdayEndTimeSurgeries;

    @Column(name="friday_start_time_surgeries")
    @Audited
    private LocalTime fridayStartTimeSurgeries;

    @Column(name="friday_end_time_surgeries")
    @Audited
    private LocalTime fridayEndTimeSurgeries;

    @Column(name="saturday_start_time_surgeries")
    @Audited
    private LocalTime saturdayStartTimeSurgeries;

    @Column(name="saturday_end_time_surgeries")
    @Audited
    private LocalTime saturdayEndTimeSurgeries;

    @Column(name="sunday_start_time_surgeries")
    @Audited
    private LocalTime sundayStartTimeSurgeries;

    @Column(name="sunday_end_time_surgeries")
    @Audited
    private LocalTime sundayEndTimeSurgeries;


}
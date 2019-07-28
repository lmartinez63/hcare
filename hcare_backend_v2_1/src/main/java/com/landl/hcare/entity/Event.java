package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;

@Entity
@Audited
public class Event extends AuditModel {

    @Id
    @GeneratedValue(generator = "event_generator")
    @SequenceGenerator(
            name = "event_generator",
            sequenceName = "event_sequence",
            allocationSize = 1,
            initialValue = 1000
    )
    private Long id;

    @Column(name="group_id")
    @Audited
    private Long groupId;

    @Column(name="title")
    @Audited
    private String title;

    @Column(name="class_names")
    @Audited
    private String classNames;

    @Column(name="editable")
    @Audited
    private Boolean editable;

    @Column(name="overlap")
    @Audited
    private Boolean overlap;

    @Column(name="start_schedule")
    @Audited
    private Date start;

    @Column(name="end_schedule")
    @Audited
    private Date end;

    @Column(name="surgery_area_id")
    @Audited
    private Long surgeryAreaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassNames() {
        return classNames;
    }

    public void setClassNames(String classNames) {
        this.classNames = classNames;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public Boolean getOverlap() {
        return overlap;
    }

    public void setOverlap(Boolean overlap) {
        this.overlap = overlap;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Long getSurgeryAreaId() {
        return surgeryAreaId;
    }

    public void setSurgeryAreaId(Long surgeryAreaId) {
        this.surgeryAreaId = surgeryAreaId;
    }
}
package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="section")
public class Section extends AuditModel {
    @Id
    @GeneratedValue(generator = "section_generator")
    @SequenceGenerator(
            name = "section_generator",
            sequenceName = "section_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    private Long id;

    @Column(name="section_code")
    private String sectionCode;

    @Column(name="visible_rule_exp")
    private String visibleRuleExp;

    @Transient
    private Boolean visible;

    @Column(name="label_code")
    private String labelCode;

    @Column(name="label_module")
    private String labelModule;

    @Column(name="label_sub_module")
    private String labelSubModule;

    @Transient
    private String labelValue;

    @Transient
    private List<FieldDefinition> fieldDefinitionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getVisibleRuleExp() {
        return visibleRuleExp;
    }

    public void setVisibleRuleExp(String visibleRuleExp) {
        this.visibleRuleExp = visibleRuleExp;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public List<FieldDefinition> getFieldDefinitionList() {
        return fieldDefinitionList;
    }

    public void setFieldDefinitionList(List<FieldDefinition> fieldDefinitionList) {
        this.fieldDefinitionList = fieldDefinitionList;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLabelModule() {
        return labelModule;
    }

    public void setLabelModule(String labelModule) {
        this.labelModule = labelModule;
    }

    public String getLabelSubModule() {
        return labelSubModule;
    }

    public void setLabelSubModule(String labelSubModule) {
        this.labelSubModule = labelSubModule;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }
}

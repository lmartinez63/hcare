package com.landl.hcare.entity;

import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.model.AuditModel;
import com.landl.hcare.rule.RuleManager;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="field_definition")
public class FieldDefinition extends AuditModel {
    @Id
    @GeneratedValue(generator = "field_def_generator")
    @SequenceGenerator(
            name = "field_def_generator",
            sequenceName = "field_def_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    private Long id;

    @Column(name="field_definition_code")
    private String fieldDefinitionCode;

    @Column(name="label_code")
    private String labelCode;

    @Column(name="label_module")
    private String labelModule;

    @Column(name="label_sub_module")
    private String labelSubModule;

    @Transient
    private String labelValue;

    //$date{{medicalAppointment.dateEntered}}
    @Column(name="definition")
    private String definition;

    @Transient
    private Object objectValue;

    @Column(name="field_type")
    private String fieldType;

    @Column(name="visible_rule_exp")
    private String visibleRuleExp;

    @Column(name="edit_rule_exp")
    private String editRuleExp;

    @Transient
    private Boolean visible;

    @Transient
    private Boolean editable;

    @Transient
    private List<Validation> validationList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldDefinitionCode() {
        return fieldDefinitionCode;
    }

    public void setFieldDefinitionCode(String fieldDefinitionCode) {
        this.fieldDefinitionCode = fieldDefinitionCode;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getVisibleRuleExp() {
        return visibleRuleExp;
    }

    public void setVisibleRuleExp(String visibleRuleExp) {
        this.visibleRuleExp = visibleRuleExp;
    }

    public String getEditRuleExp() {
        return editRuleExp;
    }

    public void setEditRuleExp(String editRuleExp) {
        this.editRuleExp = editRuleExp;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Object getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(Object objectValue) {
        this.objectValue = objectValue;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
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

    public List<Validation> getValidationList() {
        return validationList;
    }

    public void setValidationList(List<Validation> validationList) {
        this.validationList = validationList;
    }
}
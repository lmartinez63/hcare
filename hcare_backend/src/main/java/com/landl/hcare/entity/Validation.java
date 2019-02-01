package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name="validation")
public class Validation extends AuditModel {
    @Id
    @GeneratedValue(generator = "validation_generator")
    @SequenceGenerator(
            name = "validation_generator",
            sequenceName = "validation_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    private Long id;

    @Column(name="label_code")
    private String labelCode;

    @Column(name="label_module")
    private String labelModule;

    @Column(name="label_sub_module")
    private String labelSubModule;

    @Column(name="field_definition_id")
    private Long fieldDefinitionId;

    @Column(name="validation_type")
    private String validationType;

    @Transient
    private String validationMessage;


    public Validation() {
    }

    public Validation(Long id, Long fieldDefinitionId, String labelCode, String validationMessage, String validationType){
        this.id = id;
        this.fieldDefinitionId = fieldDefinitionId;
        this.labelCode = labelCode;
        this.validationMessage = validationMessage;
        this.validationType = validationType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getFieldDefinitionId() {
        return fieldDefinitionId;
    }

    public void setFieldDefinitionId(Long fieldDefinitionId) {
        this.fieldDefinitionId = fieldDefinitionId;
    }

    public String getValidationType() {
        return validationType;
    }

    public void setValidationType(String validationType) {
        this.validationType = validationType;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }
}

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

    //Map one to one association between Person and Address
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="label_id")
    private Label label;

    @Transient
    private String labelValue;

    //$date{{medicalAppointment.dateEntered}}
    @Column(name="definition")
    private String definition;

    @Transient
    private Object objectValue;

    @Column(name="field_type")
    private Integer fieldType;

    @Column(name="visible_rule_exp")
    private String visibleRuleExp;

    @Column(name="edit_rule_exp")
    private String editRuleExp;

    @Column(name="select_source")
    private String selectSource;

    @Column(name="on_click_event")
    private String onClickEvent;

    @Column(name="on_change_event")
    private String onChangeEvent;

    @Transient
    private Boolean visible;

    @Transient
    private Boolean editable;

    @Transient
    private Boolean modal;

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

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }


    public List<Validation> getValidationList() {
        return validationList;
    }

    public void setValidationList(List<Validation> validationList) {
        this.validationList = validationList;
    }

    public String getSelectSource() {
        return selectSource;
    }

    public void setSelectSource(String selectSource) {
        this.selectSource = selectSource;
    }

    public Boolean getModal() {
        return modal;
    }

    public void setModal(Boolean modal) {
        this.modal = modal;
    }

    public String getOnClickEvent() {
        return onClickEvent;
    }

    public void setOnClickEvent(String onClickEvent) {
        this.onClickEvent = onClickEvent;
    }

    public String getOnChangeEvent() {
        return onChangeEvent;
    }

    public void setOnChangeEvent(String onChangeEvent) {
        this.onChangeEvent = onChangeEvent;
    }
}
package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

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

    @Column(name="section_type")
    private Integer sectionType;

    @Column(name="visible_rule_exp")
    private String visibleRuleExp;

    @Column(name="entity")
    private String entity;

    @Column(name="visualization_order")
    private Integer visualizationOrder;

    @Transient
    private Boolean visible;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="label_id")
    private Label label;

    @Transient
    private String labelValue;

    @Transient
    private List<FieldDefinition> fieldDefinitionList;

    @Transient
    private Map<String,FieldDefinition> fieldDefinitionMap;

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

    public Map<String, FieldDefinition> getFieldDefinitionMap() {
        return fieldDefinitionMap;
    }

    public void setFieldDefinitionMap(Map<String, FieldDefinition> fieldDefinitionMap) {
        this.fieldDefinitionMap = fieldDefinitionMap;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public Integer getVisualizationOrder() {
        return visualizationOrder;
    }

    public void setVisualizationOrder(Integer visualizationOrder) {
        this.visualizationOrder = visualizationOrder;
    }

    public Integer getSectionType() {
        return sectionType;
    }

    public void setSectionType(Integer sectionType) {
        this.sectionType = sectionType;
    }
}

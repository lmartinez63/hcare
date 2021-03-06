package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name="page_button")
public class PageButton extends AuditModel {
    @Id
    @GeneratedValue(generator = "page_button_generator")
    @SequenceGenerator(
            name = "page_button_generator",
            sequenceName = "page_button_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    private Long id;

    @Column(name="visible_rule_exp")
    private String visibleRuleExp;

    @Column(name="eventDefinition")
    private String eventDefinition;

    @Column(name="button_code")
    private String buttonCode;

    @Column(name="button_type")
    private Integer buttonType;

    @Column(name="display_order")
    private Integer displayOrder;

    @Column(name="icon")
    private String icon;
    @Transient
    private String labelValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="label_id")
    private Label label;

    @Transient
    private Boolean visible;

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getVisibleRuleExp() {
        return visibleRuleExp;
    }

    public void setVisibleRuleExp(String visibleRuleExp) {
        this.visibleRuleExp = visibleRuleExp;
    }

    public String getEventDefinition() {
        return eventDefinition;
    }

    public void setEventDefinition(String eventDefinition) {
        this.eventDefinition = eventDefinition;
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getButtonType() {
        return buttonType;
    }

    public void setButtonType(Integer buttonType) {
        this.buttonType = buttonType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}

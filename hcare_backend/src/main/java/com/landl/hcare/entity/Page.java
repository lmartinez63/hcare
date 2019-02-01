package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="page")
public class Page extends AuditModel {
    @Id
    @GeneratedValue(generator = "page_generator")
    @SequenceGenerator(
            name = "page_generator",
            sequenceName = "page_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    private Long id;

    @Column(name="page_code")
    private String pageCode;

    @Column(name="visible_rule_exp")
    private String visibleRuleExp;

    @Transient
    private Boolean visible;

    /*
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "page_field", joinColumns = {
            @JoinColumn(name = "page_code", referencedColumnName ="page_code" ) }, inverseJoinColumns = {
            @JoinColumn(name = "field_definition_code", referencedColumnName ="field_definition_code") })
    */
    @Transient
    private List<Section> sectionList;

    /*
    @Transient
    private List<FieldDefinition> fieldDefinitionList;
    */

    public Page() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPageCode() {
        return pageCode;
    }

    public void setPageCode(String pageCode) {
        this.pageCode = pageCode;
    }

    public List<Section> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
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

}

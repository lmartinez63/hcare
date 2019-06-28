package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Audited
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

    @Column(name="entity")
    private String entity;

    @Column(name="visible_rule_exp")
    private String visibleRuleExp;

    @Column(name="title_definition")
    private String titleDefinition;

    @Column(name="sub_title_definition")
    private String subTitleDefinition;

    @Transient
    @NotAudited
    private Boolean visible;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="label_id")
    private Label label;

    @OneToMany
    @OrderBy("display_order")
    @JoinColumn(name = "page_id")
    private List<PageButton> pageButtons;
    /*
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "page_field", joinColumns = {
            @JoinColumn(name = "page_code", referencedColumnName ="page_code" ) }, inverseJoinColumns = {
            @JoinColumn(name = "field_definition_code", referencedColumnName ="field_definition_code") })
    */
    @Transient
    private List<Section> sectionList;

    @Transient
    private Map<String,Section> sectionMap;

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

    public Map<String, Section> getSectionMap() {
        return sectionMap;
    }

    public void setSectionMap(Map<String, Section> sectionMap) {
        this.sectionMap = sectionMap;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public List<PageButton> getPageButtons() {
        return pageButtons;
    }

    public void setPageButtons(List<PageButton> pageButtons) {
        this.pageButtons = pageButtons;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getTitleDefinition() {
        return titleDefinition;
    }

    public void setTitleDefinition(String titleDefinition) {
        this.titleDefinition = titleDefinition;
    }

    public String getSubTitleDefinition() {
        return subTitleDefinition;
    }

    public void setSubTitleDefinition(String subTitleDefinition) {
        this.subTitleDefinition = subTitleDefinition;
    }
}

package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name="role_page_section_field")
public class RolePageSectionField  extends AuditModel {

    @EmbeddedId
    private RolePageSectionFieldId id;

    @Transient
    private Role role;

    @Transient
    private Page page;

    @Transient
    private Section section;

    @Transient
    private FieldDefinition fieldDefinition;

    public RolePageSectionField() {
    }

    public RolePageSectionField(RolePageSectionFieldId id) {
        this.id = id;
    }

    public RolePageSectionFieldId getId() {
        return id;
    }

    public void setId(RolePageSectionFieldId id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public FieldDefinition getFieldDefinition() {
        return fieldDefinition;
    }

    public void setFieldDefinition(FieldDefinition fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }


}
package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class DataColumn extends AuditModel {
    @Id
    @GeneratedValue(generator = "data_column_generator")
    @SequenceGenerator(
            name = "data_column_generator",
            sequenceName = "data_column_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    private Long id;

    @Column(name="data_table_code")
    private String dataTableCode;

    @Column(name="data_column_code")
    private String dataColumnCode;

    @Column(name="data_column_name")
    private String dataColumnName;

    @Column(name="field_definition_code")
    private String fieldDefinitionCode;

    @Column(name="front_end_visibility")
    private String frontEndVisibility;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataTableCode() {
        return dataTableCode;
    }

    public void setDataTableCode(String dataTableCode) {
        this.dataTableCode = dataTableCode;
    }

    public String getDataColumnCode() {
        return dataColumnCode;
    }

    public void setDataColumnCode(String dataColumnCode) {
        this.dataColumnCode = dataColumnCode;
    }

    public String getDataColumnName() {
        return dataColumnName;
    }

    public void setDataColumnName(String dataColumnName) {
        this.dataColumnName = dataColumnName;
    }

    public String getFieldDefinitionCode() {
        return fieldDefinitionCode;
    }

    public void setFieldDefinitionCode(String fieldDefinitionCode) {
        this.fieldDefinitionCode = fieldDefinitionCode;
    }

    public String getFrontEndVisibility() {
        return frontEndVisibility;
    }

    public void setFrontEndVisibility(String frontEndVisibility) {
        this.frontEndVisibility = frontEndVisibility;
    }
}

package com.landl.hcare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.landl.hcare.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name="data_column")
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

    @Column(name="data_column_code")
    private String dataColumnCode;

    @Column(name="data_column_name")
    private String dataColumnName;

    @Column(name="field_definition_code")
    private String fieldDefinitionCode;

    @Column(name="front_end_visibility")
    private String frontEndVisibility;

    @Column(name="column_type")
    private String columnType;

    @Column(name="column_order")
    private String columnOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "data_table_id", nullable = false)
    private DataTable dataTable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public DataTable getDataTable() {
        return dataTable;
    }

    public void setDataTable(DataTable dataTable) {
        this.dataTable = dataTable;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnOrder() {
        return columnOrder;
    }

    public void setColumnOrder(String columnOrder) {
        this.columnOrder = columnOrder;
    }
}

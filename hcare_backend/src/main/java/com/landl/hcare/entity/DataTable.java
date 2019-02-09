package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class DataTable extends AuditModel {
    @Id
    @GeneratedValue(generator = "data_table_generator")
    @SequenceGenerator(
            name = "data_table_generator",
            sequenceName = "data_table_sequence",
            initialValue = 1000,
            allocationSize = 1
    )
    private Long id;

    @Column(name="data_table_code")
    private String dataTableCode;

    @Column(name="data_table_name")
    private String dataTableName;

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

    public String getDataTableName() {
        return dataTableName;
    }

    public void setDataTableName(String dataTableName) {
        this.dataTableName = dataTableName;
    }
}

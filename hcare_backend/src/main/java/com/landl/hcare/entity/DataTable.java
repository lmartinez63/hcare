package com.landl.hcare.entity;

import com.landl.hcare.model.AuditModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="data_table")
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

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "dataTable")
    private Set<DataColumn> dataColumns = new HashSet<>();

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

    public Set<DataColumn> getDataColumns() {
        return dataColumns;
    }

    public void setDataColumns(Set<DataColumn> dataColumns) {
        this.dataColumns = dataColumns;
    }

}

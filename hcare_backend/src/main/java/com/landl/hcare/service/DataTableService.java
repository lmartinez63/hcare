package com.landl.hcare.service;


import com.landl.hcare.entity.DataTable;

import java.util.List;

public interface DataTableService {

    public DataTable save(DataTable dataTable);

    public List<DataTable> findAll();

    public DataTable findById(Long dataTableId);

    public DataTable findByDataTableCode(String dataTableCode);

}

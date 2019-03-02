package com.landl.hcare.service;


import com.landl.hcare.entity.DataColumn;

import java.util.List;

public interface DataColumnService {

    public DataColumn save(DataColumn dataColumn);

    public List<DataColumn> findAll();

    public DataColumn findById(Long dataColumnId);

    public List<DataColumn> findByDataTableIdAndUsername(Long dataTableId, String username);


}

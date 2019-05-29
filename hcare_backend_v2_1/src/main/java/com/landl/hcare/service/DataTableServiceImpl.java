package com.landl.hcare.service;

import com.landl.hcare.entity.DataTable;
import com.landl.hcare.repository.DataTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataTableServiceImpl implements DataTableService {

    @Autowired
    DataTableRepository dataTableRepository;

    @Autowired
    DataColumnService dataColumnService;

    public DataTable save(DataTable dataTable){
        return dataTableRepository.save(dataTable);
    }

    public List<DataTable> findAll(){
        return dataTableRepository.findAll();
    }

    public DataTable findById(Long dataTableId){
        return dataTableRepository.findById(dataTableId).get();
    }

    public DataTable findByDataTableCode(String dataTableCode){
        return dataTableRepository.findByDataTableCode(dataTableCode).get(0);
    }

    public DataTable findByDataTableCodeAndUsername(String dataTableCode, String username){
        DataTable dataTable = dataTableRepository.findByDataTableCode(dataTableCode).get(0);
        if ( dataTable != null){
            dataTable.setDataColumns(dataColumnService.findByDataTableIdAndUsername(dataTable.getId(),username));
        }
        return dataTable;
    }

}

package com.landl.hcare.repository;

import com.landl.hcare.entity.Label;
import com.landl.hcare.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Long>{
    @Query(value = "select val.id, val.field_definition_id, val.label_code, l.label_value, validation_type from validation val \n" +
            "left join label l on l.language = :language and l.label_code = val.label_code and l.module = val.label_module and l.sub_module = val.label_sub_module\n" +
            "where val.field_definition_id = :fieldDefinitionId and val.label_code = :labelCode and val.label_module = :labelModule and val.label_sub_module = :labelSubModule", nativeQuery = true)
    List<Object[]> findByFieldDefinitionIdAndLabel(Long fieldDefinitionId, String language, String labelCode, String labelModule, String labelSubModule);
}

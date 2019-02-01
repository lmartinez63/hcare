package com.landl.hcare.service;

import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.entity.FieldDefinition;
import com.landl.hcare.entity.Label;
import com.landl.hcare.repository.FieldRepository;
import com.landl.hcare.rule.RuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class FieldServiceImpl implements FieldService{

    @Autowired
    FieldRepository fieldRepository;

    @Autowired
    LabelService labelService;

    @Autowired
    ValidationService validationService;

    public FieldDefinition save(FieldDefinition fieldDefinition){
        return fieldRepository.save(fieldDefinition);
    }

    public List<FieldDefinition> findAll(){
        return fieldRepository.findAll();
    }

    public List<FieldDefinition> getFieldsByPageSectionCodeAndUsername(String pageCode, String serviceCode, String username){
        return fieldRepository.getFieldsByPageCodeAndUsername(pageCode, serviceCode, username);
    }

    public Optional<FieldDefinition> findById(Long fieldId){
        return fieldRepository.findById(fieldId);
    }

    public void evaluateRules(FieldDefinition fieldDefinition, Map dataSource) {
        //By default set Visible to true
        fieldDefinition.setVisible(true);
        if(!UtilityTools.isEmpty(fieldDefinition.getVisibleRuleExp())){
            try{
                fieldDefinition.setVisible(RuleManager.evaluateExpression(fieldDefinition.getVisibleRuleExp(),dataSource));
            } catch(Exception e){
                e.printStackTrace();
                fieldDefinition.setVisible(false);
            }
        }
        //By default set Editable to true
        fieldDefinition.setEditable(true);
        if(!UtilityTools.isEmpty(fieldDefinition.getEditRuleExp())){
            try{
                fieldDefinition.setEditable(RuleManager.evaluateExpression(fieldDefinition.getEditRuleExp(),dataSource));
            } catch(Exception e){
                e.printStackTrace();
                fieldDefinition.setEditable(false);
            }
        }
    }

    public void evaluateFields(FieldDefinition fieldDefinition, Map dataSource) throws  Exception {
        Label label = labelService.getByLabelCodeAndUserLanguage(fieldDefinition.getLabelCode(), fieldDefinition.getLabelModule(), fieldDefinition.getLabelSubModule());
        if (label != null){
            fieldDefinition.setLabelValue(label.getLabelValue());
            fieldDefinition.setValidationList(validationService.getValidationsByFieldDefinition(fieldDefinition));
        }
        fieldDefinition.setObjectValue(UtilityTools.getObjectValue(fieldDefinition.getDefinition(), dataSource));
        //fieldDefinition.setFieldType(fieldDefinition.getObjectValue().getClass().getSimpleName());
    }
}
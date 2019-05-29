package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.entity.FieldDefinition;
import com.landl.hcare.entity.Section;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("retrieveFieldDefinitionInfo")
public class RetrieveFieldDefinitionInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final FieldDefinition fieldDefinitionRequest = mapper.convertValue(requestMap.get("fieldDefinition"), FieldDefinition.class);
        final String getLabels = (String)requestMap.get("getLabels");
        FieldDefinition fieldDefinition = null;
        if(fieldDefinitionRequest.getId() != null){
            fieldDefinition = fieldService.findById(fieldDefinitionRequest.getId());
        } else {
            fieldDefinition = fieldService.createFieldDefinition();
        }
        if(UtilityTools.isNull(getLabels).compareTo("true") == 0){
            addDataToResultMap("labelList",labelService.findAll());
        }
        addDataToResultMap("fieldDefinition",fieldDefinition);

    }



}

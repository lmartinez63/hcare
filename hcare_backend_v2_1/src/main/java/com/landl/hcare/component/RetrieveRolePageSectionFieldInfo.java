package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.entity.FieldDefinition;
import com.landl.hcare.entity.RolePageSectionField;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("retrieveRolePageSectionFieldInfo")
public class RetrieveRolePageSectionFieldInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final RolePageSectionField rolePageSectionFieldRequest = mapper.convertValue(requestMap.get("rolePageSectionField"), RolePageSectionField.class);
        RolePageSectionField rolePageSectionField = null;

        if(rolePageSectionFieldRequest.getId() != null && rolePageSectionFieldRequest.getId().getRoleId() != null && rolePageSectionFieldRequest.getId().getPageId() != null && rolePageSectionFieldRequest.getId().getSectionId() != null && rolePageSectionFieldRequest.getId().getFieldDefinitionId() != null){
            rolePageSectionField = rolePageSectionFieldService.findById(rolePageSectionFieldRequest.getId());
            rolePageSectionFieldService.getEntities(rolePageSectionField);
        } else {
            rolePageSectionField = rolePageSectionFieldService.createRolePageSectionField();
        }

        addDataToResultMap("rolePageSectionField",rolePageSectionField);

    }



}

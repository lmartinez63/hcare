package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.entity.Page;
import com.landl.hcare.entity.Section;
import com.landl.hcare.entity.UserAuthenticated;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("retrieveSectionInfo")
public class RetrieveSectionInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final Section sectionRequest = mapper.convertValue(requestMap.get("section"), Section.class);
        final String getLabels = (String)requestMap.get("getLabels");
        Section section = null;
        if(sectionRequest.getId() != null){
            section = sectionService.findById(sectionRequest.getId());
            section.setFieldDefinitionList(fieldService.getFieldsBySectionCode(section.getSectionCode()));
        } else {
            section = sectionService.createSection();
        }
        if(UtilityTools.isNull(getLabels).compareTo("true") == 0){
            addDataToResultMap("labelList",labelService.findAll());
        }
        addDataToResultMap("section",section);
    }



}

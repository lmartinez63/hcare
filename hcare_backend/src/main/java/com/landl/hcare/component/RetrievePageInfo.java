package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.entity.MedicalAppointment;
import com.landl.hcare.entity.Page;
import com.landl.hcare.entity.Patient;
import com.landl.hcare.entity.UserAuthenticated;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("retrievePageInfo")
public class RetrievePageInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final Page pageRequest = mapper.convertValue(requestMap.get("page"), Page.class);
        final UserAuthenticated userAuthenticated = (UserAuthenticated)requestMap.get("userAuthenticated");
        final String getLabels = (String)requestMap.get("getLabels");
        Page page = null;
        if(pageRequest.getId() != null){
            page = pageService.findById(pageRequest.getId());
            page.setSectionList(sectionService.getSectionsByPageCode(page.getPageCode()));
        } else {
            page = pageService.createPage();
        }
        if(UtilityTools.isNull(getLabels).compareTo("true") == 0){
            addDataToResultMap("labelList",labelService.findAll());
        }
        addDataToResultMap("page",page);
    }



}

package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.Label;
import com.landl.hcare.entity.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("retrieveLabelInfo")
public class RetrieveLabelInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        final Label labelRequest = mapper.convertValue(requestMap.get("label"), Label.class);
        Label label = null;
        if(labelRequest.getId() != null){
            label = labelService.findById(labelRequest.getId());
        } else {
            label = labelService.createLabel();
        }
        addDataToResultMap("label",label);
    }



}

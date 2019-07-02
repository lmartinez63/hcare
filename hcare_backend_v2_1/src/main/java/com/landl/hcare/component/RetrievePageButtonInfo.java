package com.landl.hcare.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landl.hcare.entity.DataTable;
import com.landl.hcare.entity.PageButton;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("retrievePageButtonInfo")
public class RetrievePageButtonInfo extends CustomProcess {

    public void executeCustomProcess(Map<String, Object> requestMap) throws Exception{

        final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
        String s_pageButtonId = (String)requestMap.get("pageButtonId");
        String s_pageId = (String)requestMap.get("pageId");
        String s_browseId = (String)requestMap.get("browseId");
        PageButton pageButton = null;
        if(s_pageButtonId != null){
            Long l_pageButtonId = Long.parseLong(s_pageButtonId);
            pageButton = pageButtonService.findById(l_pageButtonId);
        } else {
            Long l_pageId = Long.parseLong(s_pageId);
            Long l_browseId = Long.parseLong(s_browseId);
            pageButton = pageButtonService.createPageButton(l_pageId,l_browseId);
        }
        addDataToResultMap("pageButton",pageButton);
    }



}

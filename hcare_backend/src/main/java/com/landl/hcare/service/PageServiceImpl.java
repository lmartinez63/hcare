package com.landl.hcare.service;

import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.entity.*;
import com.landl.hcare.repository.PageRepository;
import com.landl.hcare.rule.RuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PageServiceImpl implements PageService {

    @Autowired
    PageRepository pageRepository;

    @Autowired
    FieldService fieldService;

    @Autowired
    SectionService sectionService;

    @Autowired
    UserService userService;

    public Page save(Page page){
        return pageRepository.save(page);
    }

    public List<Page> findAll(){
        return pageRepository.findAll();
    }

    public Page findById(Long pageId){
        return pageRepository.findById(pageId).get();
    }

    public Page createPage(){
        Page page = new Page();
        page.setVisibleRuleExp("true");
        return page;
    }

    public Page findByPageCode(String pageCode){
        return pageRepository.findByPageCode(pageCode).get(0);
    }

    public Page findByPageCodeAndUsername(String pageCode, String username){
        return pageRepository.findByPageCodeAndUsername(pageCode, username);
    }

    //TODO put on cache
    public Page findPageSectionAndFieldsByPageCodeAndUserName(String pageCode, String username) throws Exception{
        UserProfile userProfile = userService.getPageAndFieldsAssigned(username);
        Page page = findByPageCode(pageCode);
        page.setSectionList(sectionService.getSectionsByPageCodeAndUsername(pageCode, username));
        page.setSectionMap(page.getSectionList().stream().collect(Collectors.toMap(Section::getSectionCode, item -> item)));
        return page;
    }

    public void verifyIfCurrentUserIsAuthorizated(Page page,Map dataSource){
        page.setVisible(true);
        if(!UtilityTools.isEmpty(page.getVisibleRuleExp())){
            try{
                page.setVisible(RuleManager.evaluateExpression(page.getVisibleRuleExp(),dataSource));
            } catch(Exception e){
                e.printStackTrace();
                page.setVisible(false);
            }
        }
    }

    public void evaluateRules(Page page, Map dataSource) throws Exception {
        //By default set Visible to true
        for (PageButton pageButton:page.getPageButtons()) {
            pageButton.setVisible(true);
            if(!UtilityTools.isEmpty(pageButton.getVisibleRuleExp())){
                try{
                    pageButton.setVisible(RuleManager.evaluateExpression(pageButton.getVisibleRuleExp(),dataSource));
                } catch(Exception e){
                    //e.printStackTrace();
                    pageButton.setVisible(false);
                }
            }
        }
    }

    public void processFields(Page page, Map dataSource) throws Exception {
        evaluateRules(page,dataSource);
        for(Section section:page.getSectionList()){
            sectionService.evaluateRules(section, dataSource);
            //Not longer neccesary labels comes from label relation
            //sectionService.evaluateFields(section, dataSource);
            for(FieldDefinition fieldDefinition:section.getFieldDefinitionList()){
                fieldService.evaluateRules(fieldDefinition, dataSource);
                fieldService.evaluateFields(fieldDefinition, dataSource);
            }
            section.setFieldDefinitionMap(section.getFieldDefinitionList().stream().collect(Collectors.toMap(FieldDefinition::getFieldDefinitionCode, item -> item)));
        }
        page.setSectionMap(page.getSectionList().stream().collect(Collectors.toMap(Section::getSectionCode, item -> item)));
    }

}

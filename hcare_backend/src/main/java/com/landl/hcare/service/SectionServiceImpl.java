package com.landl.hcare.service;

import com.landl.hcare.common.UtilityTools;
import com.landl.hcare.entity.FieldDefinition;
import com.landl.hcare.entity.Label;
import com.landl.hcare.entity.Section;
import com.landl.hcare.repository.SectionRepository;
import com.landl.hcare.rule.RuleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SectionServiceImpl implements SectionService{

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    LabelService labelService;

    @Autowired
    FieldService fieldService;

    public Section save(Section section){
        return sectionRepository.save(section);
    }

    public List<Section> findAll(){
        return sectionRepository.findAll();
    }

    public List<Section> getSectionsByPageCodeAndUsername(String pageCode, String username){
        List<Section> sectionList = sectionRepository.getSectionsByPageCodeAndUsername(pageCode, username);
        for(Section section:sectionList){
            section.setFieldDefinitionList(fieldService.getFieldsByPageSectionCodeAndUsername(pageCode, section.getSectionCode(), username));
        }
        return sectionList;
    }

    public Optional<Section> findById(Long sectionId){
        return sectionRepository.findById(sectionId);
    }

    public void evaluateRules(Section section, Map dataSource) {
        //By default set Visible to true
        section.setVisible(true);
        if(!UtilityTools.isEmpty(section.getVisibleRuleExp())){
            try{
                section.setVisible(RuleManager.evaluateExpression(section.getVisibleRuleExp(),dataSource));
            } catch(Exception e){
                e.printStackTrace();
                section.setVisible(false);
            }
        }

    }

    public void evaluateFields(Section section, Map dataSource) throws  Exception {
        Label label = labelService.getByLabelCodeAndUserLanguage(section.getLabelCode(), section.getLabelModule(), section.getLabelSubModule());
        if (label != null){
            section.setLabelValue(label.getLabelValue());
        }
    }

}

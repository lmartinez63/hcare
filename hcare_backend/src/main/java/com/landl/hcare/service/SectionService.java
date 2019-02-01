package com.landl.hcare.service;


import com.landl.hcare.entity.Section;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SectionService {

    public Section save(Section section);

    public List<Section> findAll();

    public List<Section> getSectionsByPageCodeAndUsername(String section, String username);

    public Optional<Section> findById(Long sectionId);

    public void evaluateRules(Section section, Map dataSource);

    public void evaluateFields(Section section, Map dataSource) throws  Exception;

}

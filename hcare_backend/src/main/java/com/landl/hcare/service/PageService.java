package com.landl.hcare.service;


import com.landl.hcare.entity.Page;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PageService {

    public Page save(Page page);

    public List<Page> findAll();

    public Optional<Page> findById(Long pageId);

    public Page findByPageCode(String pageCode);

    public Page findByPageCodeAndUsername(String pageCode,String username);

    public Page findPageSectionAndFieldsByPageCodeAndUserName(String pageCode, String username);

    public void verifyIfCurrentUserIsAuthorizated(Page page, Map dataSource);

    public void processFields(Page page, Map dataSource) throws Exception;
}

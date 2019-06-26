package com.landl.hcare.model;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.context.SecurityContextHolder;

public class ExtRevListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        ExtRevEntity extRevEntity = (ExtRevEntity) revisionEntity;
        if(SecurityContextHolder.getContext().getAuthentication()  != null ){
            extRevEntity.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        }
    }
}
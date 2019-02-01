package com.landl.hcare.service;

import com.landl.hcare.entity.UserProfile;

import java.util.List;

public interface UserService {

    UserProfile save(UserProfile userProfile    );
    List<UserProfile> findAll();
    List<UserProfile> findByRole(String roleName);
    void delete(long id);
    UserProfile findOne(String username);
    UserProfile findByUsername(String username);
    UserProfile getPageAndFieldsAssigned(String username);
    UserProfile findById(Long id);
}
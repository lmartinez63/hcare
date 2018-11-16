package com.landl.hcare.service;


import com.landl.hcare.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User save(User user);

    public List<User> findAll();

    public Optional<User> findById(Long userId);

}

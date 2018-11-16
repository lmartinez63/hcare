package com.landl.hcare.controller;

import com.landl.hcare.entity.User;
import com.landl.hcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public User retrieveByPatientId(@PathVariable Long userId) {
        User user = userService.findById(userId).get();
        return user;
    }

    @PostMapping("/users")
    public User saveUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

}

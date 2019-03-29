package com.landl.hcare.service;

import com.landl.hcare.entity.Role;
import com.landl.hcare.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    public Role save(Role role){
        return roleRepository.save(role);
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }


    public Role findById(Long roleId){
        return roleRepository.findById(roleId).get();
    }

}

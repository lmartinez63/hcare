package com.landl.hcare.service;

import com.landl.hcare.entity.Employee;
import com.landl.hcare.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public List<Employee> findByTitle(String title){
        return employeeRepository.findByTitle(title);
    }

    public Optional<Employee> findById(Long employeeId){
        return employeeRepository.findById(employeeId);
    }
}

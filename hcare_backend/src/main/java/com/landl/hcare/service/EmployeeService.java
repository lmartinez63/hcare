package com.landl.hcare.service;

import com.landl.hcare.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public Employee save(Employee employee);

    public List<Employee> findAll();

    public List<Employee> findByTitle(String title);

    public Optional<Employee> findById(Long employeeId);

}

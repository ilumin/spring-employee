package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Employee;
import com.ilumin.lab.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

}

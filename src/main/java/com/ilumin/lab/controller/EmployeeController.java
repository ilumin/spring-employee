package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Employee;
import com.ilumin.lab.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Employee> getEmployees(
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) Integer size
    ) {
        Pageable pageable = new PageRequest(page<=0 ? 0 : page - 1, size);
        return employeeRepository.findAll(pageable);
    }

}

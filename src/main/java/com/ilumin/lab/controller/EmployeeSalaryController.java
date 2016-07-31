package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Salary;
import com.ilumin.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeSalaryController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees/{id}/salaries", method = RequestMethod.GET)
    public Iterable<Salary> showSalaries(@PathVariable Integer id) {
        return employeeService.showSalaries(id);
    }

    @RequestMapping(value = "/employees/{id}/salaries", method = RequestMethod.POST)
    public Iterable<Salary> addSalary(@PathVariable Integer id, @RequestBody Salary salaryData) throws Exception {
        return employeeService.addSalary(id, salaryData);
    }
}

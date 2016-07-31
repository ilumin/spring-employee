package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Employee;
import com.ilumin.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Iterable<Employee> getEmployees(
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {
        Pageable pageable = new PageRequest(page<=0 ? 0 : page - 1, size);
        return employeeService.fetchEmployee(pageable);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployee(
            @PathVariable Integer id
    ) {
        return employeeService.getEmployee(id);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public Employee newEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public Employee updateEmployee(
            @PathVariable Integer id,
            @RequestBody Employee employeeData
    ) {
        return employeeService.updateEmployee(id, employeeData);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public void removeEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

}

package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Employee;
import com.ilumin.lab.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Iterable<Employee> getEmployees(
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "20", required = false) Integer size
    ) {
        Pageable pageable = new PageRequest(page<=0 ? 0 : page - 1, size);
        return employeeRepository.findAll(pageable);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public Employee getEmployee(
            @PathVariable Integer id
    ) {
        return employeeRepository.findOne(id);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public Employee newEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public Employee updateEmployee(
            @PathVariable Integer id,
            @RequestBody Employee employeeData
    ) {
        Employee employee = employeeRepository.findOne(id);
        employee.setBirthDate(employeeData.getBirthDate());
        employee.setFirstName(employeeData.getFirstName());
        employee.setLastName(employeeData.getLastName());
        employee.setGender(employeeData.getGender());
        employee.setHireDate(employeeData.getHireDate());
        return employeeRepository.save(employee);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public void removeEmployee(@PathVariable Integer id) {
        Employee employee = employeeRepository.findOne(id);
        employeeRepository.delete(employee);
    }

}

package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Salary;
import com.ilumin.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class EmployeeSalaryController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees/{id}/salaries", method = RequestMethod.GET)
    public Resource<Iterable<Salary>> showSalaries(@PathVariable Integer id) {
        return responseAsResource(id, employeeService.showSalaries(id));
    }

    @RequestMapping(value = "/employees/{id}/salaries", method = RequestMethod.POST)
    public Resource<Iterable<Salary>> addSalary(@PathVariable Integer id, @RequestBody Salary salaryData) throws Exception {
        return responseAsResource(id, employeeService.addSalary(id, salaryData));
    }

    private Resource<Iterable<Salary>> responseAsResource(Integer id, Iterable<Salary> salaries) {
        Link self = linkTo(methodOn(EmployeeSalaryController.class).showSalaries(id)).withSelfRel();
        Link employee = linkTo(methodOn(EmployeeController.class).getEmployee(id)).withSelfRel();
        return new Resource<>(salaries, self, employee);
    }
}

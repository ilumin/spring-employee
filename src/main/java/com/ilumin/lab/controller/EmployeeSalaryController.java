package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Salary;
import com.ilumin.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Resource<Page<Salary>> showSalaries(
            @PathVariable Integer id,
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {
        Pageable pageable = new PageRequest(page<=0 ? 0 : page - 1, size);
        return responseAsResource(id, employeeService.showSalaries(id, pageable));
    }

    @RequestMapping(value = "/employees/{id}/salaries", method = RequestMethod.POST)
    public Resource<Page<Salary>> addSalary(@PathVariable Integer id, @RequestBody Salary salaryData) throws Exception {
        return responseAsResource(id, employeeService.addSalary(id, salaryData));
    }

    private Resource<Page<Salary>> responseAsResource(Integer id, Page<Salary> salaries) {
        Link self = linkTo(methodOn(EmployeeSalaryController.class).showSalaries(id, salaries.getNumber(), salaries.getSize())).withSelfRel();
        Link employee = linkTo(methodOn(EmployeeController.class).getEmployee(id)).withRel("employee");
        return new Resource<>(salaries, self, employee);
    }
}

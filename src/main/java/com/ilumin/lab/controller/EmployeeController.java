package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Employee;
import com.ilumin.lab.domain.Salary;
import com.ilumin.lab.domain.Title;
import com.ilumin.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@ExposesResourceFor(Employee.class)
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    EntityLinks entityLinks;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Iterable<Employee> getEmployees(
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {
        Pageable pageable = new PageRequest(page<=0 ? 0 : page - 1, size);
        return employeeService.fetchEmployee(pageable);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public Resource<Employee> getEmployee(
            @PathVariable Integer id
    ) {
        return responseAsResource(employeeService.getEmployee(id));
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

    private Resource<Employee> responseAsResource(Employee employee) {
        Link self = linkTo(methodOn(EmployeeController.class).getEmployee(employee.getEmpNo())).withSelfRel();
        Link titles = linkTo(methodOn(EmployeeTitleController.class).showTitles(employee.getEmpNo())).withRel("all-titles");
        Link salaries = linkTo(methodOn(EmployeeSalaryController.class).showSalaries(employee.getEmpNo())).withRel("all-salaries");
        return new Resource<>(employee, self, titles, salaries);
    }
}

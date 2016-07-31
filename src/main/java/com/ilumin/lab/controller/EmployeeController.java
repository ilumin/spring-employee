package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Employee;
import com.ilumin.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public Resource<Page<Employee>> getEmployees(
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {
        Pageable pageable = new PageRequest(page<=0 ? 0 : page - 1, size);
        Page<Employee> employees = employeeService.fetchEmployee(pageable);
        for (Employee employee : employees) {
            Link self = linkTo(methodOn(EmployeeController.class).getEmployee(employee.getEmpNo())).withSelfRel();
            employee.add(self);
        }
        Link self = linkTo(methodOn(EmployeeController.class).getEmployees(page, size)).withSelfRel();
        return new Resource<>(employees, self);
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    public Resource<Employee> getEmployee(
            @PathVariable Integer id
    ) {
        return responseAsResource(employeeService.getEmployee(id));
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public Resource<Employee> newEmployee(@RequestBody Employee employee) {
        return responseAsResource(employeeService.createEmployee(employee));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    public Resource<Employee> updateEmployee(
            @PathVariable Integer id,
            @RequestBody Employee employeeData
    ) {
        return responseAsResource(employeeService.updateEmployee(id, employeeData));
    }

    @RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    public void removeEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    private Resource<Employee> responseAsResource(Employee employee) {
        Link self = linkTo(methodOn(EmployeeController.class).getEmployee(employee.getEmpNo())).withSelfRel();
        Link titles = linkTo(methodOn(EmployeeTitleController.class).showTitles(employee.getEmpNo())).withRel("titles");
        Link salaries = linkTo(methodOn(EmployeeSalaryController.class).showSalaries(employee.getEmpNo())).withRel("salaries");
        return new Resource<>(employee, self, titles, salaries);
    }
}

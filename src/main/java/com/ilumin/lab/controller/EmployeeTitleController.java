package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Title;
import com.ilumin.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class EmployeeTitleController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees/{id}/titles", method = RequestMethod.GET)
    public Resource<Iterable<Title>> showTitles(@PathVariable Integer id) {
        return responseAsResource(id, employeeService.showTitles(id));
    }

    @RequestMapping(value = "/employees/{id}/titles", method = RequestMethod.POST)
    public Resource<Iterable<Title>> addTitle(@PathVariable Integer id, @RequestBody Title titleData) throws Exception {
        return responseAsResource(id, employeeService.addTitle(id, titleData));
    }

    private Resource<Iterable<Title>> responseAsResource(Integer id, Iterable<Title> titles) {
        Link self = linkTo(methodOn(EmployeeTitleController.class).showTitles(id)).withSelfRel();
        Link employee = linkTo(methodOn(EmployeeController.class).getEmployee(id)).withRel("employee");
        return new Resource<>(titles, self, employee);
    }

}

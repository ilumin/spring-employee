package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Title;
import com.ilumin.lab.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeTitleController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees/{id}/titles", method = RequestMethod.GET)
    public Iterable<Title> showTitles(@PathVariable Integer id) {
        return employeeService.showTitles(id);
    }

    @RequestMapping(value = "/employees/{id}/titles", method = RequestMethod.POST)
    public Iterable<Title> addTitle(@PathVariable Integer id, @RequestBody Title titleData) throws Exception {
        return employeeService.addTitle(id, titleData);
    }

}

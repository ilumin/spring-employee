package com.ilumin.lab.controller;

import com.ilumin.lab.domain.DepartmentEmployee;
import com.ilumin.lab.domain.Employee;
import com.ilumin.lab.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentEmployeeController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/departments/{deptNo}/employees")
    public Page<DepartmentEmployee> getEmployees(@PathVariable String deptNo, Pageable pageable) {
        return departmentService.getEmployees(deptNo, pageable);
    }

}

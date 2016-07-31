package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Department;
import com.ilumin.lab.service.DepartmentService;
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
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/departments", method = RequestMethod.GET)
    public Resource<Page<Department>> getDepartments(
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "5", required = false) Integer size
    ) {
        Pageable pageable = new PageRequest(page<=0 ? 0 : page - 1, size);
        Page<Department> departments = departmentService.fetchDepartment(pageable);
        for (Department department : departments) {
            Link self = linkTo(methodOn(DepartmentController.class).getDepartment(department.getDeptNo())).withSelfRel();
            department.add(self);
        }
        Link self = linkTo(methodOn(DepartmentController.class).getDepartments(page, size)).withSelfRel();
        return new Resource<>(departments, self);
    }

    @RequestMapping(value = "/departments/{deptNo}", method = RequestMethod.GET)
    public Resource<Department> getDepartment(@PathVariable String deptNo) {
        Department department = departmentService.getDepartment(deptNo);
        Link self = linkTo(methodOn(DepartmentController.class).getDepartment(deptNo)).withSelfRel();
        return new Resource<>(department, self);
    }

}

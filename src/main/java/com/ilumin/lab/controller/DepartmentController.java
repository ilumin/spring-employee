package com.ilumin.lab.controller;

import com.ilumin.lab.domain.Department;
import com.ilumin.lab.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Resource<Page<Department>> getDepartments(Pageable pageable) {
        Page<Department> departments = departmentService.fetchDepartment(pageable);
        return getPageResource(pageable, departments);
    }

    @RequestMapping(value = "/departments/{deptNo}", method = RequestMethod.GET)
    public Resource<Department> getDepartment(@PathVariable String deptNo) {
        Department department = departmentService.getDepartment(deptNo);
        return getDepartmentResource(department);
    }

    @RequestMapping(value = "/departments", method = RequestMethod.POST)
    public Resource<Department> newDepartment(@RequestBody Department departmentData) {
        Department department = departmentService.createDepartment(departmentData);
        return getDepartmentResource(department);
    }

    @RequestMapping(value = "/departments/{deptNo}", method = RequestMethod.DELETE)
    public void removeDepartment(@PathVariable String deptNo) {
        departmentService.deleteDepartment(deptNo);
    }

    private Resource<Department> getDepartmentResource(Department department) {
        Link self = linkTo(methodOn(DepartmentController.class).getDepartment(department.getDeptNo())).withSelfRel();
        return new Resource<>(department, self);
    }

    private Resource<Page<Department>> getPageResource(Pageable pageable, Page<Department> departments) {
        for (Department department : departments) {
            Link self = linkTo(methodOn(DepartmentController.class).getDepartment(department.getDeptNo())).withSelfRel();
            department.add(self);
        }
        Link self = linkTo(methodOn(DepartmentController.class).getDepartments(pageable)).withSelfRel();
        return new Resource<>(departments, self);
    }

}

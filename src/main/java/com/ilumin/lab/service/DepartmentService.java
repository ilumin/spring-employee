package com.ilumin.lab.service;

import com.ilumin.lab.domain.Department;
import com.ilumin.lab.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Page<Department> fetchDepartment(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    public Department getDepartment(String deptNo) {
        return departmentRepository.findOne(deptNo);
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(String deptNo) {
        Department department = departmentRepository.findOne(deptNo);
        departmentRepository.deleteManager(deptNo);
        departmentRepository.deleteEmployee(deptNo);
        departmentRepository.delete(department);
    }
}

package com.ilumin.lab.service;

import com.ilumin.lab.domain.Employee;
import com.ilumin.lab.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Integer id) {
        return employeeRepository.findOne(id);
    }

    public Iterable<Employee> fetchEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee updateEmployee(Integer id, Employee employeeData) {
        Employee employee = getEmployee(id);
        employee.setBirthDate(employeeData.getBirthDate());
        employee.setFirstName(employeeData.getFirstName());
        employee.setLastName(employeeData.getLastName());
        employee.setGender(employeeData.getGender());
        employee.setHireDate(employeeData.getHireDate());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        Employee employee = getEmployee(id);
        employeeRepository.delete(employee);
    }

}

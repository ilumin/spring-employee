package com.ilumin.lab.service;

import com.ilumin.lab.domain.Employee;
import com.ilumin.lab.domain.Salary;
import com.ilumin.lab.domain.Title;
import com.ilumin.lab.repository.EmployeeRepository;
import com.ilumin.lab.repository.SalaryRepository;
import com.ilumin.lab.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    SalaryRepository salaryRepository;

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

    public Iterable<Title> showTitles(Integer id) {
        return titleRepository.findByEmpNo(id);
    }

    public Iterable<Title> addTitle(Integer id, Title titleData) throws Exception {
        if (!Objects.equals(titleData.getEmpNo(), id)) {
            throw new Exception("Employee No not match");
        }
        titleRepository.save(titleData);
        return showTitles(id);
    }

    public Iterable<Salary> showSalaries(Integer id) {
        return salaryRepository.findByEmpNo(id);
    }

    public Iterable<Salary> addSalary(Integer id, Salary salaryData) throws Exception {
        if (!Objects.equals(salaryData.getEmpNo(), id)) {
            throw new Exception("Employee No not match");
        }
        salaryRepository.save(salaryData);
        return showSalaries(id);
    }
}

package com.ilumin.lab.repository;

import com.ilumin.lab.domain.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
}

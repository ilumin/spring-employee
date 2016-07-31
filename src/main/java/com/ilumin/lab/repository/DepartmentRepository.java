package com.ilumin.lab.repository;

import com.ilumin.lab.domain.Department;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, String> {
}

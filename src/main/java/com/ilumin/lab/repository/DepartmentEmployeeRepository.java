package com.ilumin.lab.repository;

import com.ilumin.lab.domain.DepartmentEmployee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface DepartmentEmployeeRepository extends PagingAndSortingRepository<DepartmentEmployee, Integer> {

    @Query(value = "SELECT de FROM DepartmentEmployee de WHERE deptNo = :deptNo")
    Page<DepartmentEmployee> findByDepartment(@Param("deptNo") String deptNo, Pageable pageable);

}

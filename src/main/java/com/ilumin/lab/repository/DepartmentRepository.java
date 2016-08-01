package com.ilumin.lab.repository;

import com.ilumin.lab.domain.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, String> {

    @Query(value = "DELETE FROM dept_manger WHERE dept_no = :deptNo", nativeQuery = true)
    void deleteManager(@Param("deptNo") String deptNo);

    @Query(value = "DELETE FROM dept_emp WHERE dept_no = :deptNo", nativeQuery = true)
    void deleteEmployee(@Param("deptNo") String deptNo);

}

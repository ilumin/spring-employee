package com.ilumin.lab.repository;

import com.ilumin.lab.domain.Salary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface SalaryRepository extends PagingAndSortingRepository<Salary, Integer> {

    @Query("SELECT s from Salary s where s.empNo = :id")
    Page<Salary> findByEmpNo(@Param("id") Integer id, Pageable pageable);

}

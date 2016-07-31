package com.ilumin.lab.repository;

import com.ilumin.lab.domain.Salary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalaryRepository extends PagingAndSortingRepository<Salary, Integer> {

    @Query("SELECT s from Salary s where s.empNo = :id")
    List<Salary> findByEmpNo(@Param("id") Integer id);

}

package com.ilumin.lab.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "dept_emp")
public class DepartmentEmployee {

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "deptNo", columnDefinition = "char(4)")
    private String deptNo;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Embedded
    @Transient
    private Employee employee;

}

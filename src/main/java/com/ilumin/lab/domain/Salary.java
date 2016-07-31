package com.ilumin.lab.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "salaries")
public class Salary {

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "salary")
    private Integer salary;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

}

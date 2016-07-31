package com.ilumin.lab.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "titles")
public class Title {

    @Id
    @Column(name = "emp_no")
    private Integer empNo;

    @Size(max = 50)
    @Column(name = "title")
    private String title;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

}

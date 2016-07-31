package com.ilumin.lab.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "employees")
public class Employee extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_no", nullable = false, unique = true)
    private Integer empNo;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "hire_date")
    private Date hireDate;

    @OneToMany(targetEntity = Title.class, mappedBy = "empNo")
    private List<Title> titles = new ArrayList<Title>();

    @Transient
    private List<Salary> salaries = new ArrayList<Salary>();

}

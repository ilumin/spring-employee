package com.ilumin.lab.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "departments")
@EqualsAndHashCode(callSuper = false)
public class Department extends ResourceSupport {

    @Id
    @Size(max = 4)
    @Column(name = "dept_no", columnDefinition = "char(4)")
    private String deptNo;

    @Column(name = "dept_name")
    private String deptName;

}

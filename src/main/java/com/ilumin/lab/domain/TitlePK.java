package com.ilumin.lab.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class TitlePK implements Serializable {

    @Column(name = "emp_no")
    private Integer empNo;

    @Column(name = "from_date")
    private Date fromDate;
}

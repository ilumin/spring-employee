package com.ilumin.lab.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "titles")
public class Title {

    @EmbeddedId
    @JsonIgnore
    private TitlePK titlePK;

    @Size(max = 50)
    @Column(name = "title")
    private String title;

    @Column(name = "from_date", insertable = false, updatable = false)
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

}

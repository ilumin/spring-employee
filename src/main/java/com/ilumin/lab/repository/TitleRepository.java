package com.ilumin.lab.repository;

import com.ilumin.lab.domain.Title;
import com.ilumin.lab.domain.TitlePK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TitleRepository extends CrudRepository<Title, TitlePK> {

    @Query("SELECT t from Title t where t.titlePK.empNo = :id")
    Iterable<Title> findByEmpNo(@Param("id") Integer id);

}

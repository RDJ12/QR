package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.Stagging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StaggingReposistory extends JpaRepository<Stagging,Integer> {
    @Query(value ="select * from stagging where caseid=:caseid",nativeQuery = true)
    List<Stagging> getStaggingRecordById(@Param("caseid") String caseid);
}

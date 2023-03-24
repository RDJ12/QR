package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.FQC;
import com.eleserv.qrCode.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FQCRepository extends JpaRepository<FQC,Integer> {
    @Query(value ="select * from fqc where caseid=:caseid",nativeQuery = true)
    List<FQC> getFQCRecordById(@Param("caseid") String caseid);
}

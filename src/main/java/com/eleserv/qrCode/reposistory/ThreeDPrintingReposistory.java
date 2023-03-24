package com.eleserv.qrCode.reposistory;


import com.eleserv.qrCode.entity.ThreeDPrinting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThreeDPrintingReposistory extends JpaRepository<ThreeDPrinting,Integer> {
    @Query(value ="select * from threedprinting where caseid=:caseid",nativeQuery = true)
    List<ThreeDPrinting> getThreeDPrintingRecordById(@Param("caseid") String caseid);
}

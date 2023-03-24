package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.Lab;
import com.eleserv.qrCode.entity.ThreeDPrinting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab,Integer> {
    @Query(value ="select * from lab where caseid=:caseid",nativeQuery = true)
    List<Lab> getLabRecordById(@Param("caseid") String caseid);
}

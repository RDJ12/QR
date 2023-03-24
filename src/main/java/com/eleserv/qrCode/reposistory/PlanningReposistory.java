package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.CCCRMEntity;
import com.eleserv.qrCode.entity.Planning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanningReposistory extends JpaRepository<Planning,Integer> {
    @Query(value ="select * from planning where case_id=:case_id",nativeQuery = true)
    List<Planning> getRecordByCaseStage(@Param("case_id") String case_id);
}

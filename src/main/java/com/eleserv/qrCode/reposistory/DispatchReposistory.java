package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.Dispatch;
import com.eleserv.qrCode.entity.Packing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DispatchReposistory extends JpaRepository<Dispatch,Integer> {
    @Query(value ="select * from dispatched_scan where case_id=:case_id",nativeQuery = true)
    List<Dispatch> getDispatchListById(@Param("case_id") String case_id);
}

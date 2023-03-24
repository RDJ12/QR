package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.Leads;
import com.eleserv.qrCode.entity.Packing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackingReposistory extends JpaRepository<Packing,Integer> {
    @Query(value ="select * from packing where case_id=:case_id ORDER BY packing_id DESC LIMIT 1 ",nativeQuery = true)
    Packing getPackingById(@Param("case_id") String case_id);
    @Query(value ="select * from packing where case_id=:case_id",nativeQuery = true)
    List<Packing> getPackingListById(@Param("case_id") String case_id);
}

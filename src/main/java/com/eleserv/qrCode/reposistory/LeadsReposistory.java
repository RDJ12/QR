package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.Leads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeadsReposistory extends JpaRepository<Leads,Integer> {
    @Query(value ="select * from leads ORDER BY id DESC",  nativeQuery = true)
    public List<Leads> getAllInReverse();
   /* @Query("SELECT u.caseid FROM alignwise_cases u WHERE u.caseid = ?1")
    String checkcaseidexistornot(String caseid);*/

    @Query(value ="select CONCAT (Case_Id,'##',Doctor_Name,'##',Patient_Name) as data from cc_crm   WHERE Case_Id=:Case_Id LIMIT 1",nativeQuery = true)
    String checkcaseidexistornot(@Param("Case_Id") String Case_Id);
    @Query(value ="SELECT Case_Id FROM render_workflow.cc_crm where Case_Id=:caseid LIMIT 1",nativeQuery = true)
    String checkcaseidexistornot1(@Param("caseid") String caseid);

}

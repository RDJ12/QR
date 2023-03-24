package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
public interface NextBatchReminderReposistory extends JpaRepository<NextBatchReminder,Integer> {
    @Query(value ="select * from next_batch_reminder WHERE next_date=:next_date ",nativeQuery = true)
     List<NextBatchReminder> getNext_Batch_details(@Param("next_date") String next_date);
    @Query(value ="SELECT case_id,upper_aligner_from,upper_aligner_to,lower_aligner_from,lower_aligner_to,date FROM dispatched_scan",nativeQuery = true)
    List<Dispatche> getALLDispatchedCases();
    @Query(value ="SELECT planning_id FROM cc_crm where  Case_id=:Case_id",nativeQuery = true)
    int getPlanningId(@Param("Case_id") Long Case_id);
    @Query(value ="SELECT upper_aligner_to FROM planning WHERE planning_id=:planning_id",nativeQuery = true)
    String getPlanningDetails(@Param("planning_id") int planning_id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE cc_crm u set next_batch_date =:next_batch_date where u.Case_Id = :Case_Id", nativeQuery = true)
    int updateUser(@Param("next_batch_date") String next_batch_date, @Param("Case_Id") Long Case_Id);
    @Query(value ="SELECT d.*,a.next_batch_date,a.planning_id FROM cc_crm as a,dispatched_scan d  WHERE a.next_batch_date>:next_date and a.next_batch_date<:next_date1 and a.Case_Id=d.case_id ",nativeQuery = true)
    Set<Dispatche> getsechuledata(@Param("next_date") String next_date, @Param("next_date1") String next_date1);
    @Query(value ="select CONCAT( upper_aligner_from,  '-',upper_aligner_to,'U ',lower_aligner_from,  '-',lower_aligner_to,'L') as total_aligner from planning where planning_id=:planning_id ",nativeQuery = true)
    String getTotalAligners(@Param("planning_id") String planning_id);
    @Transactional
    @Modifying
    @Query(value = "UPDATE cc_crm u set remark =:remark,starter_case_stage =:starter_case_stage,dispatchstrkit=now() where u.Case_Id = :Case_Id", nativeQuery = true)
    int updateCC_CRM_StarterKit_Dispatch(@Param("remark") String remark,@Param("starter_case_stage") String starter_case_stage, @Param("Case_Id") Long Case_Id);
    //remark='Test Remark',starter_case_stage='DPHSTRKIT',dispatchstrkit=now()
    @Transactional
    @Modifying
    @Query(value = "UPDATE cc_crm u set remark =:remark,case_stage =:case_stage,dispatch=now() where u.Case_Id = :Case_Id", nativeQuery = true)
    int updateCC_CRM_Dispatch(@Param("remark") String remark,@Param("case_stage") String case_stage, @Param("Case_Id") Long Case_Id);
    @Query(value = "Call NEXT_BATCH_REPORT(?1,?2,?3)", nativeQuery = true)
    List<NextBatchReport> getNextBatchReport(String Case_Type, String FromDate, String ToDate);
}

package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.math.BigInteger;

public interface CCCRMEntityReposistory extends JpaRepository<CCCRMEntity, Long> {

    @Query(value = "select * from CC_CRM where case_stage=:case_stage", nativeQuery = true)
    List<CCCRMEntity> getRecordByCaseStage(@Param("case_stage") String case_stage);

    @Query(value = "select * from CC_CRM where starter_case_stage=:starter_case_stage", nativeQuery = true)
    List<CCCRMEntity> getRecordByStarterkitCaseStage(@Param("starter_case_stage") String starter_case_stage);

    @Transactional
    @Modifying
    @Query(value = "update CC_CRM set next_batch_date=:next_batch_date where Case_Id=:Case_Id", nativeQuery = true)
    int updateNextBatchDateCCCRM(@Param("next_batch_date") Timestamp next_batch_date, @Param("Case_Id") Long Case_Id);

    @Transactional
    @Modifying
    @Query(value = "update CC_CRM set registered_doctor=:registered_doctor, clinic_Name=:clinic_Name, crm_name=:crm_name, case_type=:case_type, scan=:scan, location=:location, city=:city, p_graph=:p_graph, starter_kit=:starter_kit, type_request=:type_request, s_shown=:s_shown, ppf_fill=:ppf_fill, case_stage=:case_stage, priority=:priority, draft=:draft, remark=:remark, holdflag=:holdflag, user_id=:user_id, starter_satus=:starter_satus, ini_at=:ini_at, address1=:address1, address2=:address2, address3=:address3, address4=:address4, address5=:address5, phone1=:phone1, phone2=:phone2, phone3=:phone3, phone4=:phone4, phone5=:phone5, location2=:location2, location3=:location3, location4=:location4, location5=:location5, city2=:city2, city3=:city3, city4=:city4, city5=:city5, default_starterkit=:default_starterkit, pincode1=:pincode1, pincode2=:pincode2, pincode3=:pincode3, pincode4=:pincode4, pincode5=:pincode5, default_address=:default_address, patient_Name=:patient_Name, doctor_Name=:doctor_Name where Case_Id=:case_id", nativeQuery = true)
    int updateCCCRMData(@Param("registered_doctor") String registered_doctor,
                        @Param("clinic_Name") String clinic_Name, @Param("crm_name") String crm_name,
                        @Param("case_type") String case_type, @Param("scan") String scan, @Param("location") String location,
                        @Param("city") String city, @Param("p_graph") String p_graph, @Param("starter_kit") String starter_kit,
                        @Param("type_request") String type_request, @Param("s_shown") String s_shown,
                        @Param("ppf_fill") String ppf_fill, @Param("case_stage") String case_stage,
                        @Param("priority") String priority, @Param("draft") String draft, @Param("remark") String remark,
                        @Param("holdflag") String holdflag, @Param("user_id") String user_id,
                        @Param("starter_satus") String starter_satus, @Param("ini_at") Date ini_at,
                        @Param("address1") String address1, @Param("address2") String address2, @Param("address3") String address3,
                        @Param("address4") String address4, @Param("address5") String address5, @Param("phone1") String phone1,
                        @Param("phone2") String phone2, @Param("phone3") String phone3, @Param("phone4") String phone4,
                        @Param("phone5") String phone5, @Param("location2") String location2, @Param("location3") String location3,
                        @Param("location4") String location4, @Param("location5") String location5, @Param("city2") String city2,
                        @Param("city3") String city3, @Param("city4") String city4, @Param("city5") String city5,
                        @Param("default_starterkit") String default_starterkit, @Param("pincode1") String pincode1,
                        @Param("pincode2") String pincode2, @Param("pincode3") String pincode3, @Param("pincode4") String pincode4,
                        @Param("pincode5") String pincode5, @Param("default_address") String default_address,
                        @Param("patient_Name") String patient_Name, @Param("doctor_Name") String doctor_Name,
                        @Param("case_id") Long case_id);
    @Transactional
    @Modifying
    @Query(value = "update CC_CRM set case_stage='UPL', upl_at=:upl_at where Case_Id=:Case_Id", nativeQuery = true)
    int updatePlanStage(@Param("Case_Id") Long Case_Id, @Param("upl_at") LocalDateTime date);

    @Transactional
    @Modifying
    @Query(value = "update CC_CRM set case_stage='Dispatched', dispatch=:dispatch where Case_Id=:Case_Id", nativeQuery = true)
    int updateBatchStage(@Param("Case_Id") Long Case_Id, @Param("dispatch") LocalDateTime date);

    @Transactional
    @Modifying
    @Query(value = "update CC_CRM set starter_case_stage='DPHSTRKIT', dispatchstrkit=:dispatchstrkit where Case_Id=:Case_Id", nativeQuery = true)
    int updateStarterkitStage(@Param("Case_Id") Long Case_Id, @Param("dispatchstrkit") LocalDateTime date);

    @Transactional
    @Modifying
    @Query(value = "call Payment_Bill_Status(?1,?2,?3)", nativeQuery = true)
    List<PaymentBillStatus> updatePayment_Bill_Status(String ids,String status,String remarks);

    @Query(value = "Call Watts_Dispatch_Reports(?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    List<WattsDispatchReports> getWattsDispatchReports(String FromDate, String ToDate, String Case_Type, String Dispatch_Type, String Request_Type, String Extra_Aligner, String Payment_Status);

    @Query(value = "Call payment_report(?1,?2,?3)", nativeQuery = true)
    List<PaymentReport> getPaymentReport(String FromDate, String ToDate, String Case_Type);

    @Query(value = "Call SP_Case_Status(?1,?2,?3,?4)", nativeQuery = true)
    List<CaseStatusReport> getCaseStatusReport(String Case_Type, String Case_Status, String FromDate, String ToDate);


}

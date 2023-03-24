package com.eleserv.qrCode.reposistory;

import com.eleserv.qrCode.entity.DecisionHistory;
import com.eleserv.qrCode.entity.FQC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface DecisionHistoryRepository extends JpaRepository<DecisionHistory, Integer> {

    @Query(value = "select * from decision_history where caseid=:caseid", nativeQuery = true)
    List<DecisionHistory> getDecisionHistoryRecordById(@Param("caseid") String caseid);

    @Query(value = "select * from decision_history where userId=:userId and DATE(date_time)>=:fromDate and DATE(date_time)<=:toDate", nativeQuery = true)
    List<DecisionHistory> getDecisionHistoryByUser(@Param("fromDate") String fromDate, @Param("toDate") String toDate,
                                                   @Param("userId") String userId);

    @Query(value = "select * from decision_history where userId=:userId and DATE(date_time) >= DATE(NOW()) - INTERVAL 7 DAY", nativeQuery = true)
    List<DecisionHistory> getLastSevenDaysDecisionHistoryByUser(@Param("userId") String userId);

    @Query(value = "select * from decision_history where userId=:userId and DATE(date_time)=:fromDate", nativeQuery = true)
    List<DecisionHistory> getDecisionHistoryByUserSingleDay(@Param("fromDate") String fromDate,
                                                            @Param("userId") String userId);

    @Query(value = "select count(distinct caseid) as cases, userid from decision_history where DATE(date_time)>=:fromDate and userid not in('','null') group by UserId", nativeQuery = true)
    List<Map> getCaseCountSingleDay(@Param("fromDate") String fromDate);

    @Query(value = "select count(distinct caseid) as cases, userid from decision_history where DATE(date_time)>=:fromDate and DATE(date_time)<=:toDate and userid not in('','null') group by UserId", nativeQuery = true)
    List<Map> getCaseCount(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value = "select count(distinct caseid) as cases, userid from decision_history where userid not in('','null') and DATE(date_time) >= DATE(NOW()) - INTERVAL 7 DAY group by UserId", nativeQuery = true)
    List<Map> getCaseCountLastSevenDays();

    @Query(value = "select a.Users as userid, a.Stage as stage, a.Total as cases from (select coalesce(UserId,'Total') as Users,coalesce(stage,'All Stage') as Stage,count(distinct caseid) as Total from decision_history where DATE(Date_time)>=:fromDate group by UserId,stage with rollup)a where a.Users not in('Total','','null') and a.Stage not in('All Stage','Hold','UnHold')", nativeQuery = true)
    List<Map> getStageCaseCountSingleDay(@Param("fromDate") String fromDate);

    @Query(value = "select a.Users as userid, a.Stage as stage, a.Total as cases from (select coalesce(UserId,'Total') as Users,coalesce(stage,'All Stage') as Stage,count(distinct caseid) as Total from decision_history where DATE(Date_time)>=:fromDate and DATE(Date_time)<=:toDate group by UserId,stage with rollup)a where a.Users not in('Total','','null') and a.Stage not in('All Stage','Hold','UnHold')", nativeQuery = true)
    List<Map> getStageCaseCount(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value = "select a.Users as userid, a.Stage as stage, a.Total as cases from (select coalesce(UserId,'Total') as Users,coalesce(stage,'All Stage') as Stage,count(distinct caseid) as Total from decision_history where DATE(date_time) >= DATE(NOW()) - INTERVAL 7 DAY group by UserId,stage with rollup)a where a.Users not in('Total','','null') and a.Stage not in('All Stage','Hold','UnHold')", nativeQuery = true)
    List<Map> getStageCaseCountLastSevenDays();

}

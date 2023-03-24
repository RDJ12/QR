package com.eleserv.qrCode.reposistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eleserv.qrCode.entity.Account;
import com.eleserv.qrCode.entity.DecisionHistory;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "select * from account where DATE(date)>=:fromDate and DATE(date)<=:toDate", nativeQuery = true)
    List<Account> getAccountsData(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value = "select * from account where DATE(date)=:date", nativeQuery = true)
    List<Account> getAccountsDataByDate(@Param("date") String date);

    @Query(value = "Select * from account where Date(date) >= DATE(NOW()) - INTERVAL 7 DAY", nativeQuery = true)
    List<Account> getLastSevenDaysAccountData();

    @Query(value = "Select * from account where case_id=:caseId order by date desc limit 1", nativeQuery = true)
    Account findByCaseId(@Param("caseId") String caseId);
}

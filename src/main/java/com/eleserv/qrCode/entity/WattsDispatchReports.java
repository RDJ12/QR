package com.eleserv.qrCode.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface WattsDispatchReports {

    String getdispatched_id();
    String getcase_id();
    String getPatient_name();
    String getdoctor_name();
    String getcrm();
    String getphone();
    String getlocation();
    String getcity();
    String getPincode();
    String getRequest_Type();
    String getPayment_Mode();
    Integer getTotal_Amount();
    Integer getReceived_Amount();
    Integer getRemain_Amount();
    LocalDateTime getLast_Payment_Date();
    String getplanning_id();
    LocalDate getplan_date();
    String getUpper_Planned();
    String getLower_Planned();
    String getUpper_Att();
    String getLower_att();
    String getExtra_Upper_Batches();
    String getExtra_Lower_Batches();
    String getUpper_Dispatched();
    String getLower_Dispatched();
    String getmode_of_dispatch();
    String gettracking_id();
    String getdelivery_nn();
    LocalDateTime getDispatched_date();
    String getPayment_Bill_status();
    String getPayment_Bill_status_Remarks();

}

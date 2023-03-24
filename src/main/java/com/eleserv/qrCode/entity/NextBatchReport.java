package com.eleserv.qrCode.entity;

import java.time.LocalDateTime;

public interface NextBatchReport {

    String getCase_id();
    String getPatient_name();
    String getDoctor_name();
    String getCrm_name();
    String getPlanning_id();
    String getPlanned_upper_from();
    String getPlanned_upper_to();
    String getPlanned_lower_from();
    String getPlanned_lower_to();
    LocalDateTime getDispatched_Date();
    String getDispatch_upper_from();
    String getDispatch_upper_to();
    String getDispatch_lower_from();
    String getDispatch_lower_to();
    LocalDateTime getNEXT_UPPER_REMINDER_DATE();
    LocalDateTime getNEXT_LOWER_REMINDER_DATE();
    String getPayment_bill_status();
    String getPayment_bill_status_remarks();
}

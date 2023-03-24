package com.eleserv.qrCode.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface PaymentReport {

    String getCase_id();
    String getPatient_name();
    String getDoctor_name();
    String getCrm();
    String getCase_Types();
    Integer getTotal_amount();
    Integer getBilling_amount();
    Integer getPaid_amount();
    Integer getRemain_amount();
    Integer getRevoke_amount();
    Integer getTotal_Received_amount();
    LocalDateTime getPayment_date();
    String getInvoice_No();
    LocalDate getInvoice_Date();
    LocalDate getCase_creation_date();

}

package com.eleserv.qrCode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispatchUpdateReport {

    private String ids;
    private String status;
    private String remarks;
    private String caseId;
    private String billingAmount;
    private String paidAmount;
    private String modeOfPayment;
    private String invoiceNumber;
    private String invoiceDate;
    private String username;
    private String doctorName;
    private String patientName;
    private String upperDispatched;
    private String lowerDispatched;

}

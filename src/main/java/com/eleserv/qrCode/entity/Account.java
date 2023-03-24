package com.eleserv.qrCode.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_id;
    private String crm;
    private String case_id;
    private String doctor_name;
    private String patient_name;
    private String payment_mode;
    private String payment;
    private Date date;
    private String remain_amount;
    private String paid_amount;
    private String submitted_amount;
    private String revoke_amount;
    private String remarks;
    private String user;
    @Column(name="invoice_date")
    private String invoiceDate;
    @Column(name="invoice_no")
    private String invoiceNumber;
    private String billing_amount;
}

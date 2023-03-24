package com.eleserv.qrCode.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "account_change")
public class AccountChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_change_id;
    private String case_id;
    private String previous_amount;
    private String new_amount;
    private String reason;
    private String user;
    private LocalDateTime date = LocalDateTime.now();
    @Column(name="invoice_date")
    private String invoiceDate;
    @Column(name="invoice_no")
    private String invoiceNumber;
    private String billing_amount;
}

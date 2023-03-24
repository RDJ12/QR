package com.eleserv.qrCode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "execute_decision_history")
public class ExecuteDecitionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String user_name;
    private String dispatched_id;
    private String case_id;
    private String doctor_name;
    private String patient_name;
    private String upper_dispatched;
    private String lower_dispatched;
    private String payment_bill_status;
    private String payment_bill_status_remarks;
}

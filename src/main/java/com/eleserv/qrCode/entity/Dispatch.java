package com.eleserv.qrCode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="dispatched_scan")
public class Dispatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dispatched_id;
    private String case_id;
    private String doctor_name;
    private String patient_name;
    private String crm;
    private String Sr_no;
    private String item;
    private String dispatch;
    private String dispatch_no;
    private String tracking_id;
    private String delivery_nn;
    private String Type_of_dispatch;
    private String decesion;
    private String remark;
    private LocalDateTime date = LocalDateTime.now();
    private String no_of_aligners;
    private String mode_of_dispatch;
    private String address;
    private String default_address;
    private String confirm_status;
    private String upper_aligner_from;
    private String upper_aligner_to;
    private String lower_aligner_from;
    private String lower_aligner_to;
    private String upper_att;
    private String lower_att;
    @Transient
    private String userId;
}

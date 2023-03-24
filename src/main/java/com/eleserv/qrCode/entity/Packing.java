package com.eleserv.qrCode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="packing")
public class Packing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packing_id;
    private String crm ;
    private String doctor_name ;
    private String patient_name ;
    private String ultra_sonic;
    private String air ;
    private String pouch_seal ;
    private String decesion;
    private String remark ;
    private String date ;
    private String case_id ;
    private String confirm_status;
}

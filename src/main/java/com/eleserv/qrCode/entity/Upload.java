package com.eleserv.qrCode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="upload")
public class Upload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int upload_id;
    private String pre_post;
    private String crm;
    private String doctor_name;
    private String patient_name;
    private String gif;
    private String case_id;
    private String report;
    private String ipr_sheet;
    private String treat_report;
    private String ipr_filled;
    private String decesion;
    private String remark;
    Date date;
}

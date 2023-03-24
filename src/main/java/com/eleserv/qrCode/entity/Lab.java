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
@Table(name="lab")
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lab_id;
    private String making;
    private String waxing;
    private String thermoform;
    private String crm;
    private String doctor_name;
    private String patient_name;
    private String caseid;
    private String from_type;
    private String to_type;
    private String name_cat;
    private String hand_tooling;
    private String decesion;
    private String remark;
    Date date;
    private String upper_aligner_from;
    private String upper_aligner_to;
    private String lower_aligner_from;
    private String lower_aligner_to;
    private String upper_att;
    private String lower_att;
}

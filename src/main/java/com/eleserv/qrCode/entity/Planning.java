package com.eleserv.qrCode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="planning")
public class Planning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planning_id;
    private String crm;
    private String doctor_name;
    private String patient_name;
    private String case_id;
    private String planning_type;
    private String base_segment;
    private String planned;
    private String ipr_sheet;
    private String treat_report;
    private String upload_digiplan;
    private String plan_review;
    private String decesion;
    private String  remark;
    private String from_type;
    private String to_type;
    Date date;
    private String planned_no;
    private String upper_aligner_from;
    private String upper_aligner_to;
    private String lower_aligner_from;
    private String lower_aligner_to;
}

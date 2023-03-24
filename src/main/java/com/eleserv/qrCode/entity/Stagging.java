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
@Table(name="stagging")
public class Stagging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planning_id;
    private String staging;
    private String crm;
    private String planning_type;
    private String d_name;
    private String caseid;
    private String from_type;
    private String to_type;
    private String aln_upper;
    private String aln_lower;
    private String thick_upper;
    private String thick_lower;
    private String margin_upper;
    private String margin_lower;
    private String molor_upper;
    private String molor_lower;
    private String sheet_type;
    private String review_attach;
    private String ipr_sheet_;
    private String soft_tissus;
    Date date;
    private String patient_name;
    private String decesion;
    private String remark;
    private String upper_aligner_from;
    private String upper_aligner_to;
    private String lower_aligner_from;
    private String lower_aligner_to;
}

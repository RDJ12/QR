package com.eleserv.qrCode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="next_batch_reminder")
public class NextBatchReminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nextbtch_id;
    private String Case_Id;
    private String Patient_Name;
    private String Doctor_Name;
    private String no_of_aligners;
    private String upper_aligner_from;
    private String upper_aligner_to;
    private String lower_aligner_from;
    private String lower_aligner_to;
    private int upper_date_in_days;
    private int lower_date_in_days;
    private String dispatch_date;
    private String next_date;
}

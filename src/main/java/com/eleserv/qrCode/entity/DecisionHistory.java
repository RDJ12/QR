package com.eleserv.qrCode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="decision_history")
public class DecisionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int decision_id;
    private String decision;
    private String Remarks;
    LocalDateTime date_time= LocalDateTime.now();
    private String stage;
    private String UserId;
    private String caseid;

}

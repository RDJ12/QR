package com.eleserv.qrCode.entity;

import java.time.LocalDateTime;

public interface CaseStatusReport {

    String getCase_id();
    String getPatient_name();
    String getDoctor_name();
    String getCrm_name();
    String getUser_Name();
    String getStatus_Type();
    String getRemarks();
    LocalDateTime getInsertDatetime();
}

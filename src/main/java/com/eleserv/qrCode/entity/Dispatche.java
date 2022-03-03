package com.eleserv.qrCode.entity;

import java.util.Date;

public interface Dispatche {
     String getCase_id();
     String getUpper_aligner_from();
     String getUpper_aligner_to();
     String getLower_aligner_from();
     String getLower_aligner_to();
     String getDate();
     String getPatient_name();
     String getDoctor_name();
     String getNext_batch_date();
     String getPlanning_id();
}

package com.eleserv.qrCode.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="cc_crm")
@DynamicUpdate
public class CCCRMEntity {
    @Id
    Long Case_Id;
    String Patient_Name;
    String Doctor_Name;
    String registered_doctor;
    String clinic_Name;
    String address;
    String phone;
    @Temporal(TemporalType.TIMESTAMP)
    Date plan_date;
    @Temporal(TemporalType.TIME)
    Date plan_time;
    String crm_name;
    String delivery_note_no;
    String bclinic;
    String badd;
    String bgst_no;
    String category;
    String upper_aligner;
    String lower_aligner;
    String others;
    String case_type;
    String  scan;
    String location;
    String city;
    String r_Doctor;
    String crm;
    String p_graph;
    String starter_kit;
    String type_request;
    String s_shown;
    String ppf_fill;
    String t_account;
    String corporate_account;
    String kol;
    String c_pkg;
    String pkg_name;
    String dispatch_crpt;
    String dispatch_address;
    String bill_address;
    String c_doctor;
    String case_stage;
    String priority;
    String draft;
    String remark;
    String holdflag;
    String user_id;
    @Temporal(TemporalType.TIMESTAMP)
    Date CREATED_DATE = new Timestamp(new Date().getTime());
    String starter_case_stage;
    String starter_satus;
    Double total_amount;
    String payment_processing;
    String payment_mode;
    @Temporal(TemporalType.TIMESTAMP)
    Date inicor_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date wfc_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date precor_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date pre_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date plncor_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date cadbs_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date pln_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date uplcor_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date  upl_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date rev_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date qa_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date stg_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date mpt_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date labcor_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date lab_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date fqc_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date pckcor_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date pck_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date dispatch;
    @Temporal(TemporalType.TIMESTAMP)
    Date inistrkit_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date mtpstrkit_at;
   /* @Temporal(TemporalType.TIMESTAMP)
    Date 3dpstrkit_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date 3dpstrkitcor_at;*/
    @Temporal(TemporalType.TIMESTAMP)
    Date labstrkit_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date labstrkitcor_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date fqcstrkit_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date pckstrkit_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date pckstrkitcor_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date dispatchstrkit;
    String planning_id;
    String staging_id;
    @Temporal(TemporalType.TIMESTAMP)
    Date ini_at;
    @Temporal(TemporalType.TIMESTAMP)
    Date wfccor_at;
    String address1;
    String address2;
    String address3;
    String address4;
    String address5;
    String phone1;
    String phone2;
    String phone3;
    String phone4;
    String phone5;
    String location2;
    String location3;
    String location4;
    String location5;
    String city2;
    String city3;
    String city4;
    String city5;
    String default_starterkit;
    String pincode1;
    String pincode2;
    String pincode3;
    String pincode4;
    String pincode5;
    String default_address;
    @Temporal(TemporalType.TIMESTAMP)
    Date next_batch_date;
}

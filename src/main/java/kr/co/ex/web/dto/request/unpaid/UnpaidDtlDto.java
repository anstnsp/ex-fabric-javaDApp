package kr.co.ex.web.dto.request.unpaid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UnpaidDtlDto {

    private String work_dates;
    private String tolof_cd;
    private String work_no;
    private String vhcl_pros_no;
    private String ecard_mnrc_clss_cd;
    private String hoinst_cd;
    private String vhno;
    private String viol_user_nm;
    private String pass_dates;
    private String pass_hms;
    private String mnrc_yn;
    private String wout_tolof_cd;
    private String wout_tolof_nm;
    private String enrc_tolof_cd;
    private String enrc_tolof_nm;
    private String ptha_enrc_tolof_cd;
    private String ptha_wout_tolof_cd;
    private String mnrc_vhcl_pros_no;
    private String mnrc_scdl_toll;
    private String mnrc_atoll;
    private String mnrc_scdl_tot_amt;
    private String mnrc_toll;
    private String mnrc_actno;
    private String ntpd_mnrc_stat_cd;
    
}
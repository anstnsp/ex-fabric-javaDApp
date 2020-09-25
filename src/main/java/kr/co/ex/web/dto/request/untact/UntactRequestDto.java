package kr.co.ex.web.dto.request.untact;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UntactRequestDto {

    private String vhno;
    private String civil_form_type_cd;
    private String aplct_user_nm;
    private String civil_form_type_nm;
    private String pros_dates;
    private String pros_st_hms;
    private String enrc_tolof_cd;
    private String enrc_tolof_nm;
    private String wout_tolof_cd;
    private String wout_tolof_nm;
    private String pass_hms;
    private String aplct_dates;
    private String aplct_hms;
    private String aplct_resn;
    private String proc_stat_cd;
    private String proc_stat_nm;
    private String attfl_unq_no;
    private String acpt_dttm;
    private String acpt_bsop_work_no;
    private String invg_rslt;
    private String invg_dttm;
    private String invg_bsop_work_no;
    
}
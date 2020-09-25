package kr.co.ex.web.dto.request.pass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PassSaveDto {
  
  private String work_dates; 
  private String tolof_cd;
  private String work_no;
  private String vhcl_pros_no;
  private String work_nm;
  private String vhno;
  private String frkncr_cd;
  private String enrcp_dates;
  private String enrcp_hms;
  private String enrc_hoinst_cd;
  private String enrc_tolof_cd;
  private String enrc_crgw_no;
  private String enrc_work_no;
  private String enrc_vhcl_pros_no;
  private String woutp_dates;
  private String woutp_hms;
  private String wout_hoinst_cd;
  private String wout_hoinst_nm;
  private String lkdc_toll;
  private String mnrc_scdl_toll;
  private String hoinst1_cd;
  private String hoinst1_nm;
  private String hoinst1_amt;
  private String hoinst2_cd;
  private String hoinst2_nm;
  private String hoinst2_amt;
  private String hoinst3_cd;
  private String hoinst3_nm;
  private String hoinst3_amt;
  private String hoinst4_cd;
  private String hoinst4_nm;
  private String hoinst4_amt;
  private String hoinst5_cd;
  private String hoinst5_nm;
  private String hoinst5_amt;
  private String elct_cdno;
  private String obu_no; 
}
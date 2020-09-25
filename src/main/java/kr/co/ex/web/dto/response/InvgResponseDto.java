package kr.co.ex.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString 
@AllArgsConstructor
public class InvgResponseDto {
  
  private String work_dates; 
  private String tolof_cd; 
  private String work_no; 
  private String vhcl_pros_no; 
  private String ocrn_tolof_cd; 
  private String pros_tord; 
  private String vio_type; 
  private String ocrn_dates; 
  private String ocrn_hms; 
  private String woutp_tolof_cd; 
  private String woutp_dates; 
  private String woutp_hms; 
  private String vhno; 
  private String frkncr_cd; 
  private String mnrc_scdl_toll; 
  private String mnrc_scdl_atoll; 
  private String wout_toll_pros_type_cd; 
  private String mnrc_cash_amt; 
  private String pecard_amt; 
  private String mnrc_decard_amt; 
  private String pecard_no; 
  private String mnrc_decard_no; 
  private String crgw_systm_clss_cd; 
  private String chng_bef_vhno; 
  private String chng_aft_vhno; 
  private String chng_bef_mnrc_scdl_toll; 
  private String chng_bef_mnrc_scdl_atoll; 
  private String chng_bef_enrc_tolof_cd; 
  private String chng_bef_enrc_tolof_nm; 
  private String chng_bef_bsop_workr_no; 
  private String chng_aft_mnrc_scdl_toll; 
  private String chng_aft_mnrc_scdl_atoll; 
  private String chng_aft_enrc_tolof_cd; 
  private String chng_aft_enrc_tolof_nm; 
  private String chng_aft_bsop_workr_no; 
  private String lsttm_modfr_id; 
  private String lsttm_altr_dttm; 
  private String vhno_chng_yn; 
  private String ntpd_chng_yn; 
  private String clsn_yn; 
  private String invg_pros_cd; 
  private String invg_pros_nm; 
  private String type_invg_cd; 
  private String iver_id; 
  private String iver_nm; 
  private String hipas_dcex_cd; 
  private String dc_amt; 
  private String excg_toll; 
  private String paym_pros_dc_rt; 
  private String paym_pros_excg_rt; 
  
}
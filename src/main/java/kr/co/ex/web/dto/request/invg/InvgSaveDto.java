package kr.co.ex.web.dto.request.invg;



import kr.co.ex.domain.insinfo.InsInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class InvgSaveDto {

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

  public InsInfo toEntity() {
    return InsInfo.builder().workDates(work_dates)
    .tolofCd(tolof_cd)
    .workNo(work_no)
    .vhclProsNo(vhcl_pros_no)
    .ocrnTolofCd(ocrn_tolof_cd)
    .prosTord(pros_tord)
    .vioType(vio_type)
    .ocrnDates(ocrn_dates)
    .ocrnHms(ocrn_hms)
    .woutpTolofCd(woutp_tolof_cd)
    .woutpDates(woutp_dates)
    .woutpHms(woutp_hms)
    .vhno(vhno)
    .frkncrCd(frkncr_cd)
    .mnrcScdlToll(mnrc_scdl_toll)
    .mnrcScdlAtoll(mnrc_scdl_atoll)
    .woutTollProsTypeCd(wout_toll_pros_type_cd)
    .mnrcCashAmt(mnrc_cash_amt)
    .pecardAmt(pecard_amt)
    .mnrcDecardAmt(mnrc_decard_amt)
    .pecardNo(pecard_no)
    .mnrcDecardNo(mnrc_decard_no)
    .crgwSystmClssCd(crgw_systm_clss_cd)
    .chngBefVhno(chng_bef_vhno)
    .chngAftVhno(chng_aft_vhno)
    .chngBefMnrcScdlToll(chng_bef_mnrc_scdl_toll)
    .chngBefMnrcScdlAtoll(chng_bef_mnrc_scdl_atoll)
    .chngBefEnrcTolofCd(chng_bef_enrc_tolof_cd)
    .chngBefEnrcTolofNm(chng_bef_enrc_tolof_nm)
    .chngBefBsopWorkrNo(chng_bef_bsop_workr_no)
    .chngAftMnrcScdlToll(chng_aft_mnrc_scdl_toll)
    .chngAftMnrcScdlAtoll(chng_aft_mnrc_scdl_atoll)
    .chngAftEnrcTolofCd(chng_aft_enrc_tolof_cd)
    .chngAftEnrcTolofNm(chng_aft_enrc_tolof_nm)
    .chngAftBsopWorkrNo(chng_aft_bsop_workr_no)
    .lsttmModfrId(lsttm_modfr_id)
    .lsttmAltrDttm(lsttm_altr_dttm)
    .vhnoChngYn(vhno_chng_yn)
    .ntpdChngYn(ntpd_chng_yn)
    .clsnYn(clsn_yn)
    .invgProsCd(invg_pros_cd)
    .invgProsNm(invg_pros_nm)
    .iverId(iver_id)
    .iverNm(iver_nm)
    .hipasDcexCd(hipas_dcex_cd)
    .dcAmt(dc_amt)
    .excgToll(excg_toll)
    .paymProsDcRt(paym_pros_dc_rt)
    .paymProsExcgRt(paym_pros_excg_rt)
    .build();
  
  }

}
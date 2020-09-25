package kr.co.ex.domain.insinfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.co.ex.domain.BaseTimeEntity;
import kr.co.ex.web.dto.response.InvgResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "INS_INFO")
public class InsInfo extends BaseTimeEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "INS_INFO_ID")
  private Long id; 

  @Column(name = "WORK_DATES")
  private String workDates;
  @Column(name = "TOLOF_CD")
  private String tolofCd;
  @Column(name = "WORK_NO")
  private String workNo;
  @Column(name = "VHCL_PROS_NO")
  private String vhclProsNo;
  @Column(name = "OCRN_TOLOF_CD")
  private String ocrnTolofCd;
  @Column(name = "PROS_TORD")
  private String prosTord;
  @Column(name = "VIO_TYPE")
  private String vioType;
  @Column(name = "OCRN_DATES")
  private String ocrnDates;
  @Column(name = "OCRN_HMS")
  private String ocrnHms;
  @Column(name = "WOUTP_TOLOF_CD")
  private String woutpTolofCd;
  @Column(name = "WOUTP_DATES")
  private String woutpDates;
  @Column(name = "WOUTP_HMS")
  private String woutpHms;
  @Column(name = "VHNO")
  private String vhno;
  @Column(name = "FRKNCR_CD")
  private String frkncrCd;
  @Column(name = "MNRC_SCDL_TOLL")
  private String mnrcScdlToll;
  @Column(name = "MNRC_SCDL_ATOLL")
  private String mnrcScdlAtoll;
  @Column(name = "WOUT_TOLL_PROS_TYPE_CD")
  private String woutTollProsTypeCd;
  @Column(name = "MNRC_CASH_AMT")
  private String mnrcCashAmt;
  @Column(name = "PECARD_AMT")
  private String pecardAmt;
  @Column(name = "MNRC_DECARD_AMT")
  private String mnrcDecardAmt;
  @Column(name = "PECARD_NO")
  private String pecardNo;
  @Column(name = "MNRC_DECARD_NO")
  private String mnrcDecardNo;
  @Column(name = "CRGW_SYSTM_CLSS_CD")
  private String crgwSystmClssCd;
  @Column(name = "CHNG_BEF_VHNO")
  private String chngBefVhno;
  @Column(name = "CHNG_AFT_VHNO")
  private String chngAftVhno;
  @Column(name = "CHNG_BEF_MNRC_SCDL_TOLL")
  private String chngBefMnrcScdlToll;
  @Column(name = "CHNG_BEF_MNRC_SCDL_ATOLL")
  private String chngBefMnrcScdlAtoll;
  @Column(name = "CHNG_BEF_ENRC_TOLOF_CD")
  private String chngBefEnrcTolofCd;
  @Column(name = "CHNG_BEF_ENRC_TOLOF_NM")
  private String chngBefEnrcTolofNm;
  @Column(name = "CHNG_BEF_BSOP_WORKR_NO")
  private String chngBefBsopWorkrNo;
  @Column(name = "CHNG_AFT_MNRC_SCDL_TOLL")
  private String chngAftMnrcScdlToll;
  @Column(name = "CHNG_AFT_MNRC_SCDL_ATOLL")
  private String chngAftMnrcScdlAtoll;
  @Column(name = "CHNG_AFT_ENRC_TOLOF_CD")
  private String chngAftEnrcTolofCd;
  @Column(name = "CHNG_AFT_ENRC_TOLOF_NM")
  private String chngAftEnrcTolofNm;
  @Column(name = "CHNG_AFT_BSOP_WORKR_NO")
  private String chngAftBsopWorkrNo;
  @Column(name = "LSTTM_MODFR_ID")
  private String lsttmModfrId;
  @Column(name = "LSTTM_ALTR_DTTM")
  private String lsttmAltrDttm;
  @Column(name = "VHNO_CHNG_YN")
  private String vhnoChngYn;
  @Column(name = "NTPD_CHNG_YN")
  private String ntpdChngYn;
  @Column(name = "CLSN_YN")
  private String clsnYn;
  @Column(name = "INVG_PROS_CD")
  private String invgProsCd;
  @Column(name = "INVG_PROS_NM")
  private String invgProsNm;
  @Column(name = "TYPE_INVG_CD")
  private String typeInvgCd;
  @Column(name = "IVER_ID")
  private String iverId;
  @Column(name = "IVER_NM")
  private String iverNm;
  @Column(name = "HIPAS_DCEX_CD")
  private String hipasDcexCd;
  @Column(name = "DC_AMT")
  private String dcAmt;
  @Column(name = "EXCG_TOLL")
  private String excgToll;
  @Column(name = "PAYM_PROS_DC_RT")
  private String paymProsDcRt;
  @Column(name = "PAYM_PROS_EXCG_RT")
  private String paymProsExcgRt;


  public InvgResponseDto toDto() {
    return new InvgResponseDto(workDates, tolofCd, workNo, vhclProsNo, ocrnTolofCd, 
    prosTord, vioType, ocrnDates, ocrnHms, woutpTolofCd, woutpDates, woutpHms, vhno, 
    frkncrCd, mnrcScdlToll, mnrcScdlAtoll, woutTollProsTypeCd, mnrcCashAmt, pecardAmt, 
    mnrcDecardAmt, pecardNo, mnrcDecardNo, crgwSystmClssCd, chngBefVhno, chngAftVhno, 
    chngBefMnrcScdlToll, chngBefMnrcScdlAtoll, chngBefEnrcTolofCd, chngBefEnrcTolofNm,
    chngBefBsopWorkrNo, chngAftMnrcScdlToll, chngAftMnrcScdlAtoll, chngAftEnrcTolofCd,
    chngAftEnrcTolofNm, chngAftBsopWorkrNo, lsttmModfrId, lsttmAltrDttm, vhnoChngYn, 
    ntpdChngYn, clsnYn, invgProsCd, invgProsNm, typeInvgCd, iverId, iverNm, hipasDcexCd,
    dcAmt, excgToll, paymProsDcRt, paymProsExcgRt); 
    
  }
  // @Column(name = "WORK_DATES")
  // private String work_dates;
  // @Column(name = "TOLOF_CD")
  // private String tolof_cd;
  // @Column(name = "WORK_NO")
  // private String work_no;
  // @Column(name = "VHCL_PROS_NO")
  // private String vhcl_pros_no;
  // @Column(name = "OCRN_TOLOF_CD")
  // private String ocrn_tolof_cd;
  // @Column(name = "PROS_TORD")
  // private String pros_tord;
  // @Column(name = "VIO_TYPE")
  // private String vio_type;
  // @Column(name = "OCRN_DATES")
  // private String ocrn_dates;
  // @Column(name = "OCRN_HMS")
  // private String ocrn_hms;
  // @Column(name = "WOUTP_TOLOF_CD")
  // private String woutp_tolof_cd;
  // @Column(name = "WOUTP_DATES")
  // private String woutp_dates;
  // @Column(name = "WOUTP_HMS")
  // private String woutp_hms;
  // @Column(name = "VHNO")
  // private String vhno;
  // @Column(name = "FRKNCR_CD")
  // private String frkncr_cd;
  // @Column(name = "MNRC_SCDL_TOLL")
  // private String mnrc_scdl_toll;
  // @Column(name = "MNRC_SCDL_ATOLL")
  // private String mnrc_scdl_atoll;
  // @Column(name = "WOUT_TOLL_PROS_TYPE_CD")
  // private String wout_toll_pros_type_cd;
  // @Column(name = "MNRC_CASH_AMT")
  // private String mnrc_cash_amt;
  // @Column(name = "PECARD_AMT")
  // private String pecard_amt;
  // @Column(name = "MNRC_DECARD_AMT")
  // private String mnrc_decard_amt;
  // @Column(name = "PECARD_NO")
  // private String pecard_no;
  // @Column(name = "MNRC_DECARD_NO")
  // private String mnrc_decard_no;
  // @Column(name = "CRGW_SYSTM_CLSS_CD")
  // private String crgw_systm_clss_cd;
  // @Column(name = "CHNG_BEF_VHNO")
  // private String chng_bef_vhno;
  // @Column(name = "CHNG_AFT_VHNO")
  // private String chng_aft_vhno;
  // @Column(name = "CHNG_BEF_MNRC_SCDL_TOLL")
  // private String chng_bef_mnrc_scdl_toll;
  // @Column(name = "CHNG_BEF_MNRC_SCDL_ATOLL")
  // private String chng_bef_mnrc_scdl_atoll;
  // @Column(name = "CHNG_BEF_ENRC_TOLOF_CD")
  // private String chng_bef_enrc_tolof_cd;
  // @Column(name = "CHNG_BEF_ENRC_TOLOF_NM")
  // private String chng_bef_enrc_tolof_nm;
  // @Column(name = "CHNG_BEF_BSOP_WORKR_NO")
  // private String chng_bef_bsop_workr_no;
  // @Column(name = "CHNG_AFT_MNRC_SCDL_TOLL")
  // private String chng_aft_mnrc_scdl_toll;
  // @Column(name = "CHNG_AFT_MNRC_SCDL_ATOLL")
  // private String chng_aft_mnrc_scdl_atoll;
  // @Column(name = "CHNG_AFT_ENRC_TOLOF_CD")
  // private String chng_aft_enrc_tolof_cd;
  // @Column(name = "CHNG_AFT_ENRC_TOLOF_NM")
  // private String chng_aft_enrc_tolof_nm;
  // @Column(name = "CHNG_AFT_BSOP_WORKR_NO")
  // private String chng_aft_bsop_workr_no;
  // @Column(name = "LSTTM_MODFR_ID")
  // private String lsttm_modfr_id;
  // @Column(name = "LSTTM_ALTR_DTTM")
  // private String lsttm_altr_dttm;
  // @Column(name = "VHNO_CHNG_YN")
  // private String vhno_chng_yn;
  // @Column(name = "NTPD_CHNG_YN")
  // private String ntpd_chng_yn;
  // @Column(name = "CLSN_YN")
  // private String clsn_yn;
  // @Column(name = "INVG_PROS_CD")
  // private String invg_pros_cd;
  // @Column(name = "INVG_PROS_NM")
  // private String invg_pros_nm;
  // @Column(name = "TYPE_INVG_CD")
  // private String type_invg_cd;
  // @Column(name = "IVER_ID")
  // private String iver_id;
  // @Column(name = "IVER_NM")
  // private String iver_nm;
  // @Column(name = "HIPAS_DCEX_CD")
  // private String hipas_dcex_cd;
  // @Column(name = "DC_AMT")
  // private String dc_amt;
  // @Column(name = "EXCG_TOLL")
  // private String excg_toll;
  // @Column(name = "PAYM_PROS_DC_RT")
  // private String paym_pros_dc_rt;
  // @Column(name = "PAYM_PROS_EXCG_RT")
  // private String paym_pros_excg_rt;

}
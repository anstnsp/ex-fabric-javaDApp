package kr.co.ex.web.dto.request.fadj;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LinkedDiscountSaveDto {
    private String fadj_yyyymm;
    private String paym_kind_cd;
    private String paym_kind_nm;
    private String fadj_kind1_trcn;
    private String fadj_kind1_amt;
    private String fadj_kind2_trcn;
    private String fadj_kind2_amt;
    private String fadj_kind3_trcn;
    private String fadj_kind3_amt;
    private String fadj_kind4_trcn;
    private String fadj_kind4_amt;
    private String fadj_kind5_trcn;
    private String fadj_kind5_amt;
    private String fadj_smcar_trcn;
    private String fadj_smcar_amt;
    private String ntpd_kind1_trcn;
    private String ntpd_kind1_amt;
    private String ntpd_kind2_trcn;
    private String ntpd_kind2_amt;
    private String ntpd_kind3_trcn;
    private String ntpd_kind3_amt;
    private String ntpd_kind4_trcn;
    private String ntpd_kind4_amt;
    private String ntpd_kind5_trcn;
    private String ntpd_kind5_amt;
    private String ntpd_smcar_trcn;
    private String ntpd_smcar_amt;
    private String dc_trcn;
    private String dc_amt;
    private String mndc_pct20_kind4_trcn;
    private String mndc_pct20_kind4_amt;
    private String mndc_pct30_kind4_trcn;
    private String mndc_pct30_kind4_amt;
    private String mndc_pct50_kind4_trcn;
    private String mndc_pct50_kind4_amt;
    private String mndc_pct20_kind5_trcn;
    private String mndc_pct20_kind5_amt;
    private String mndc_pct30_kind5_trcn;
    private String mndc_pct30_kind5_amt;
    private String mndc_pct50_kind5_trcn;
    private String mndc_pct50_kind5_amt;
    private String fadj_sum_trcn;
    private String fadj_sum_atm;

}
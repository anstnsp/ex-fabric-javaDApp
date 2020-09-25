package kr.co.ex.web.dto.request.fadj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthCalcuateSaveDto {
    
    private String fadj_yyyymm;
    private String bond_ntpd_amt;
    private String bond_rfnd_amt;
    private String bond_sbtt_amt;
    private String debt_ntpd_amt;
    private String debt_rfnd_amt;
    private String debt_sbtt_amt;
    private String lkdc_toll;
    private String itrs_ntpd_tot_amt;
    private String itrs_ntpd_crdt_amt;
    private String itrs_ntpd_crdt_fee_amt;

    
}


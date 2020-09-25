package kr.co.ex.web.dto.request.fadj;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BondRefundSaveDto {
    private String fadj_yyyymm;
    private String hdqr_cd;
    private String hdqr_nm;
    private String tot_rfnd_amt;
    private String ex_rfnd_amt;
    private String itrs_rfnd_amt;
    private String fadj_amt;
    
}
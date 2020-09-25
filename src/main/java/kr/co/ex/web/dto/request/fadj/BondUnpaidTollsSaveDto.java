package kr.co.ex.web.dto.request.fadj;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BondUnpaidTollsSaveDto {
    private String fadj_yyyymm;
    private String tolof_cd;
    private String tolof_nm;
    private String cnt;
    private String tot_mnrc_amt;
    private String ex_income_amt;
    private String itrs_income_amt;
    private String fadj_amt;
}
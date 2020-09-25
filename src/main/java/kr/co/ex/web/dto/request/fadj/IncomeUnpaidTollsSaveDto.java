package kr.co.ex.web.dto.request.fadj;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IncomeUnpaidTollsSaveDto {
    private String fadj_yyyymm;
    private String hdqr_cd;
    private String hdqr_nm;
    private String cnt;
    private String tot_mnrc_amt;
    private String ex_income_amt_sum;
    private String ex_income_amt_cash;
    private String ex_income_amt_crdt_norm;
    private String ex_income_amt_crdt_nbmr;
    private String itrs_income_amt_sum;
    private String itrs_imcome_amt_cash;
    private String itrs_income_amt_crdt_norm;
    private String itrs_income_amt_crdt_nbmr;
    private String fadj_amt;
}
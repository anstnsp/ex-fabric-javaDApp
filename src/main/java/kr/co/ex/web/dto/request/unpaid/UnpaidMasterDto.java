package kr.co.ex.web.dto.request.unpaid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UnpaidMasterDto {
    private String work_dates;
    private String tolof_cd;
    private String work_no;
    private String vhcl_pros_no;
    private String viol_user_nm;
    private String ntpd_amt;
    private String ntpd_mnrc_scdl_toll;
}
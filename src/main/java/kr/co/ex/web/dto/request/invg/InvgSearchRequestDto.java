package kr.co.ex.web.dto.request.invg;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvgSearchRequestDto {
  private String work_dates;
  private String tolof_cd;
  private String work_no;
  private String vhcl_pros_no;
  private String vhno_chng_yn;
  private String ntpd_chng_yn;
}
package kr.co.ex.web.dto.request.mnrcinfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MnrcInfoSaveDto {
  private String work_dates;    //근무일자
  private String tolof_cd;      //출구영업소코드
  private String work_no;       //출구근무번호
  private String vhcl_pros_no;  //차량처리번호

  private String mnrc_toll;     //수납통행료
  private String mnrc_clss_cd;  //수납구분코드
  private String mnrcp_dates;   //수납처리일자
  private String will_amt;      //수납예정통행료
  private String mnrc_cash_amt; //수납현금금액
  private String mnrc_ecard_amt;//수납전자카드금액
}
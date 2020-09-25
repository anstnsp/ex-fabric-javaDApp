package kr.co.ex.web.dto.request.unpaid;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnpaidEventDto {
  private String work_dates;        //근무일자
  private String tolof_cd;          //영업소코드
  private String work_no;           //근무번호
  private String vhcl_pros_no;      //차량처리번호
  private String ecard_mnrc_clss_cd;//전자카드수납구분코드
  private String hoinst_cd;         //고속도로운영기관코드
  private String mnrc_yn;           //수납여부
  private String mnrc_bank;         //수납은행
  private String mnrc_date;         //수납일자
  private String mnrc_scdl_toll;    //수납통행료
  private String mnrc_scdl_atoll;   //수납부가퉁행료
  private String mnrc_scdl_tot_amt; //수납총금액
  private String mnrc_vhcl_pros_no; //수납차량처리번호
  private String viol_cd;           //위반코드
  private String viol_nm;           //위반명
  private String token;             //개인식별자
  private String pk_token;          //PK Hash 

  private Long blockNumber;         //이벤트로 받은 블럭
  private String txId;              //이벤트로 받은 트랜잭션
  private boolean isValid;          //유효한트랜잭션인지
  private String peer;              //이벤트준 피어 
}
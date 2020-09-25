package kr.co.ex.web.dto.request.askinfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AskInfoSaveDto {

  private String work_dates;    //근무일자
  private String tolof_cd;      //출구영업소코드
  private String work_no;       //출구근무번호
  private String vhcl_pros_no;  //차량처리번호
  private String ask_dates;     //청구일자
}
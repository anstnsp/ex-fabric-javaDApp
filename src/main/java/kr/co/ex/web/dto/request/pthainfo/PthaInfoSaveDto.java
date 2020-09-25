package kr.co.ex.web.dto.request.pthainfo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PthaInfoSaveDto {
  private String work_dates;    //근무일자
  private String tolof_cd;      //출구영업소코드
  private String work_no;       //출구근무번호
  private String vhcl_pros_no;  //차량처리번호

  private String odn1_ptha_tolof_cd;      //1번째경유지영업소코드
  private String odn1_ptha_tolof_nm;      //1번째경유지영업소명
  private String odn1_ptha_enrcp_dates;   //1번째경유지입구처리일자
  private String odn1_ptha_enrcp_hms;     //1번째경유지입구처리시분초
  private String odn1_wout_tolof_cd;      //1번째경유지출구영업소코드
  private String odn1_wout_tolof_nm;      //1번째경유지출구영업소명
  private String odn1_woutp_dates;        //1번째경유지출구처리일자
  private String odn1_wout_hms;           //1번째경유지출구처리시분초
  private String odn2_ptha_tolof_cd;      //2번째경유지엉업소코드
  private String odn2_ptha_tolof_nm;      //2번재경유지영업소명
  private String odn2_ptha_enrcp_dates;   //2번째경유지입구처리일자
  private String odn2_ptha_enrcp_hms;     //2번째경유지입구처리시분초
  private String odn2_wout_tolof_cd;      //2번째경유지출구영업소코드
  private String odn2_wout_tolof_nm;      //2번째경유지출구영업소명
  private String odn2_woutp_dates;        //2번째경유지출구처리일자
  private String odn2_woutp_hms;          //2번째경유지출구처리시분초
  private String odn3_ptha_tolof_cd;      //3번째경유지영업소코드
  private String odn3_ptha_tolof_nm;      //3번째경유지영업소명
  private String odn3_ptha_enrcp_dates;   //3번째경유지입구처리일자
  private String odn3_ptha_enrcp_hms;     //3번째경유지입구처리시분초
  private String odn3_wout_tolof_cd;      //3번째경유지출구영업소코드
  private String odn3_wout_tolof_nm;      //3번째경유지출구영업소명
  private String odn3_woutp_dates;        //3번째경유지출구처리일자
  private String odn3_woutp_hms;          //3번째경유지출구처리시분초
  private String odn4_ptha_tolof_cd;      //4번째경유지영업소코드
  private String odn4_ptha_tolof_nm;      //4번째경유지영업소명
  private String odn4_ptha_enrcp_dates;   //4번째경유지입구처리일자
  private String odn4_ptha_enrcp_hms;     //4번재경유지입구처리시분초
  private String odn4_wout_tolof_cd;      //4번째경유지출구영업소코드
  private String odn4_wout_tolof_nm;      //4번째경유지출구영업소명
  private String ond4_woutp_dates;        //4번째경유지출구처리일자
  private String odn4_woutp_hms;          //4번째경유지출구처리시분초 
  private String odn5_ptha_tolof_cd;      //5번째경유지영업소코드
  private String odn5_ptha_tolof_nm;      //5번째경유지영업소명
  private String odn5_ptha_enrcp_dates;   //5번째경유지입구처리일자
  private String odn5_ptha_enrcp_hms;     //5번째경유지입구처리시분초
  private String odn5_wout_tolof_cd;      //5번째경유지출구영업소코드
  private String odn5_wout_tolof_nm;      //5번째경유지출구영업소명
  private String odn5_woutp_dates;        //5번째경유지출구처리일자
  private String odn5_woutp_hms;          //5번째경유지출구처리시분초
  
}
package kr.co.ex.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ChaincodeFunction {
  SET_PASSINFO(0, "setPassInfo"), //통행정보저장
  GET_PASSINFO(1, "getPassInfo"), //키로조회
  GET_PASSINFOS(2, "getPassInfos"), //월별조회
  SET_INVG(3, "setInvg"),  //영업심사정보저장, 통합심사정보 저장.
  SET_UNPAID(4, "setUnpaid"), // 미납정보저장
  GET_UNPAID(5, "getUnpaidCarMgr"), //미납차량조회
  SET_SUMMST(6, "setFadjSumMst"), //월정산집계저장
  SET_SUMDTL01(7, "setFadjSumDtl01"), //채 권(도로공사 수입금) 미납통행료 정보 전송
  SET_SUMDTL02(8, "setFadjSumDtl02"), //채 권(도로공사 수입금) 환불금 정보 전송
  SET_SUMDTL03(9, "setFadjSumDtl03"), //대구부산 수입금 미납통행료 : 신용카드 수수료 차감_총금액 정보 전송
  SET_SUMDTL04(10, "setFadjSumDtl04"), //채 무(대구부산 수입금)_환불금 정보 전송
  SET_SUMDTL05(11, "setFadjSumDtl05"), //연계할인 통행료 정보 전송
  GET_SUMMST(12, "getFadjSumMst"), //월정산 집계 결과 조회
  GET_SUMDTL01(13, "getFadjSumDtl01"), //도로공사 미납통행료 수입금 조회
  GET_SUMDTL02(14, "getFadjSumDtl02"), //도로공사 환불금 수입금 조회
  GET_SUMDTL03(15, "getFadjSumDtl03"), //신대구부산고속도로 미납통행료 수입금 조회
  GET_SUMDTL04(16, "getFadjSumDtl04"), //신대구부산고속도로 환불금 수입금 조회
  GET_SUMDTL05(17, "getFadjSumDtl05"), //연계할인 통행료 조회
  SAVE_ASKDATA(18, "saveAskData"),    //청구정보전송
  SAVE_MNRCDATA(19, "saveMnrcData"),  //수납정보전송
  SAVE_WOUTDATA(20, "saveWoutData"),  //경유지정보전송
  SAVE_RSTUNPAID(21, "setRstUnpaidPDC"), //납부결과저장
  GET_RSTUNPAID(22, "getMergeRstUnpaidPDC"), //통합된납부결과데이터 조회 
  SAVE_CIVIL(23, "saveCivilData"); //비대면신청전송
  
  private final int index;
  private final String value; 
}
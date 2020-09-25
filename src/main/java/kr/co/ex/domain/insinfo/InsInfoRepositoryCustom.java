package kr.co.ex.domain.insinfo;

import java.util.List;

public interface InsInfoRepositoryCustom {
  
  List<InsInfo> findDynamicQuery(
    String workDates, 
    String tolofCd,
    String workNo,
    String vhclProsNo,
    String vhnoChngYn,
    String ntpdChngYn
  );

}


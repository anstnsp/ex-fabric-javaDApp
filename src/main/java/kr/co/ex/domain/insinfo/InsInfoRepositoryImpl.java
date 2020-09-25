package kr.co.ex.domain.insinfo;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import static kr.co.ex.domain.insinfo.QInsInfo.insInfo;


@RequiredArgsConstructor
public class InsInfoRepositoryImpl implements InsInfoRepositoryCustom {

  private final JPAQueryFactory queryFactory; 

  @Override
  @Transactional(readOnly = true)
  public List<InsInfo> findDynamicQuery(String workDates, String tolofCd, String workNo, String vhclProsNo,
      String vhnoChngYn, String ntpdChngYn) {

    return queryFactory
              .selectFrom(insInfo)
              .where(
                eqVhnoChngYn(vhnoChngYn),
                eqNtpdChngYn(ntpdChngYn)
              )
              .fetch();
  }
  
  private BooleanExpression eqVhnoChngYn(String vhnoChngYn) {
    if (StringUtils.isEmpty(vhnoChngYn)) return null;
    return insInfo.vhnoChngYn.eq(vhnoChngYn);
  }
  private BooleanExpression eqNtpdChngYn(String ntpdChngYn) {
    if (StringUtils.isEmpty(ntpdChngYn)) return null;
    return insInfo.ntpdChngYn.eq(ntpdChngYn);
  }

  
}
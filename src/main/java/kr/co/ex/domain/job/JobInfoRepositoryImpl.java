package kr.co.ex.domain.job;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import static  kr.co.ex.domain.job.QJobInfo.jobInfo;

@RequiredArgsConstructor
public class JobInfoRepositoryImpl implements JobInfoRepositoryCustom {

  private final JPAQueryFactory queryFactory; 
  
  @Override
  public List<JobInfo> findJobInfo(String job_id, String pros_dates) {
    return queryFactory
              .selectFrom(jobInfo)
              .groupBy(jobInfo.job_id, jobInfo.job_seq)
              .where(
                eqJobId(job_id),
                eqProsDates(pros_dates)
              )
              .orderBy(jobInfo.job_id.asc(), jobInfo.pros_dates.asc())
              .fetch();
  }


  private BooleanExpression eqJobId(String job_id) {
    if (StringUtils.isEmpty(job_id)) return null;
    return jobInfo.job_id.eq(job_id);
  }
  
  private BooleanExpression eqProsDates(String pros_dates) {
    if (StringUtils.isEmpty(pros_dates)) return null;
    return jobInfo.pros_dates.eq(pros_dates);
  }

}
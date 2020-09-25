package kr.co.ex.domain.job;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface JobInfoRepository extends JpaRepository<JobInfo, Long>, JobInfoRepositoryCustom {
    
  List<Object[]> groupQuery(@Param("job_id") String job_id, @Param("job_seq") String job_seq, @Param("pros_dates") String prod_dates );

}


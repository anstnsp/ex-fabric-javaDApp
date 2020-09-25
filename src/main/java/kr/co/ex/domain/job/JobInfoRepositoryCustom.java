package kr.co.ex.domain.job;

import java.util.List;

public interface JobInfoRepositoryCustom {

  List<JobInfo> findJobInfo(String job_id, String pros_dates);  
 
}
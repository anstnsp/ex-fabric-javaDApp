package kr.co.ex.web.dto.request;

import kr.co.ex.domain.job.JobInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JobRequestDto {
  
  private String job_id;
  private String job_nm;
  private String job_seq;
  private String pros_dates;
  private String pros_st_hms;
  private String pros_ed_hms;


  
  public JobInfo toEntity() {
    return JobInfo.builder().job_id(job_id).job_nm(job_nm).job_seq(job_seq)
            .pros_dates(pros_dates).pros_st_hms(pros_st_hms).pros_ed_hms(pros_ed_hms).build();
  }
}
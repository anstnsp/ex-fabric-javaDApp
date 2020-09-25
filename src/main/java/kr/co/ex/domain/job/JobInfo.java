package kr.co.ex.domain.job;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import kr.co.ex.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "JOB_INFO")
@SqlResultSetMapping(
  name = "jobinfo",
  entities = {
    @EntityResult(entityClass = JobInfo.class)
  }
)
@NamedNativeQuery(
  name = "JobInfo.groupQuery",
  query = "select ji.job_info_id, ji.job_id, ji.job_nm, ji.job_seq, ji.pros_dates, ji.created_date, ji.modified_date, ji.pros_ed_hms, ji.pros_st_hms " +
        "from ( select j.*," +
        "      ROW_NUMBER() over(partition by j.job_id, j.job_seq, j.PROS_DATES  order by (j.CREATED_DATE) desc) as gn " +
        "from JOB_INFO j " +
        "WHERE JOB_ID = :job_id AND JOB_SEQ <= :job_seq AND PROS_DATES = :pros_dates) ji "  +
        "where gn =1",
  resultSetMapping = "jobinfo"
)
public class JobInfo extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "JOB_INFO_ID")
  private Long id; 

  @Column(name = "JOB_ID", nullable = false)
  private String job_id;
  @Column(name = "JOB_NM")
  private String job_nm;
  @Column(name = "JOB_SEQ")
  private String job_seq;
  @Column(name = "PROS_DATES")
  private String pros_dates;
  @Column(name = "PROS_ST_HMS")
  private String pros_st_hms;
  @Column(name = "PROS_ED_HMS")
  private String pros_ed_hms; 


  @Builder
  public JobInfo(String job_id, String job_nm, String job_seq, String pros_dates,
                String pros_st_hms, String pros_ed_hms) {
          this.job_id = job_id;
          this.job_nm = job_nm; 
          this.job_seq = job_seq;
          this.pros_dates = pros_dates;
          this.pros_st_hms = pros_st_hms;
          this.pros_ed_hms = pros_ed_hms;
  }
}
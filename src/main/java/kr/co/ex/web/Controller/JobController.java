package kr.co.ex.web.Controller;

import java.util.List;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ex.domain.job.JobInfo;
import kr.co.ex.domain.job.JobInfoRepository;
import kr.co.ex.exception.RequiredParamException;
import kr.co.ex.web.dto.request.JobRequestDto;
import kr.co.ex.web.dto.response.common.ResponseSearchResult;
import kr.co.ex.web.dto.response.common.ResponseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class JobController {

  private static final Logger logger = LoggerFactory.getLogger(JobController.class);
  private final JobInfoRepository jobInfoRepository; 
  private final ResponseService responseService; 

  //데이터전송확인(DM-DP006001)
  @PostMapping("/getJobInfo")
  public ResponseSearchResult getJobInfo(@RequestBody JobRequestDto jobRequestDto) {
    logger.debug("========== getJobInfo START ==========");
    logger.debug("jobRequestDto: {}", jobRequestDto.toString());
    if (StringUtils.isEmpty(jobRequestDto.getJob_id()) || StringUtils.isEmpty(jobRequestDto.getJob_seq()) || 
        StringUtils.isEmpty(jobRequestDto.getPros_dates())) {
        logger.error("job_id: {}, job_seq: {}, pros_dates: {}", jobRequestDto.getJob_id(), jobRequestDto.getJob_seq(), jobRequestDto.getPros_dates());
        throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
    }
    List<Object[]> queryData = jobInfoRepository.groupQuery(jobRequestDto.getJob_id(), jobRequestDto.getJob_seq(), jobRequestDto.getPros_dates());
    // [ [{}], [{}], [{}] ]
    List<JobInfo> jobInfoList =queryData.stream().map(row -> (JobInfo) row[0]).collect(Collectors.toList()); 
    return responseService.getListResult(jobInfoList);
  }

  @PostMapping("/searchBlockchainSendInfo")
  public ResponseSearchResult getJobInfo2(@RequestBody JobRequestDto jobRequestDto) {
    logger.debug("========== getJobInfo2 START ==========");
    logger.debug("jobRequestDto: {}", jobRequestDto.toString());

    if (StringUtils.isEmpty(jobRequestDto.getPros_dates()) ) {
      logger.error("pros_dates: {}", jobRequestDto.getPros_dates());
      throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
    }

    List<JobInfo> jobList = jobInfoRepository.findJobInfo(jobRequestDto.getJob_id(), jobRequestDto.getPros_dates());
    return responseService.getListResult(jobList);
  }

}
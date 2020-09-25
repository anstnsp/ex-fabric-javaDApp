package kr.co.ex.web.Controller;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.ContractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ex.exception.RequiredParamException;
import kr.co.ex.service.PthaInfoService;
import kr.co.ex.web.dto.request.JobRequestDto;
import kr.co.ex.web.dto.request.pthainfo.PthaInfoSaveDto;
import kr.co.ex.web.dto.request.pthainfo.PthaInfoSaveRequestDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import kr.co.ex.web.dto.response.common.ResponseResult;
import kr.co.ex.web.dto.response.common.ResponseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PthaInfoController {
  private static final Logger logger = LoggerFactory.getLogger(PthaInfoController.class);
  private final ResponseService responseService;
  private final PthaInfoService pthaInfoSevice;

  @PostMapping("/saveWoutData")
  public ResponseResult saveWoutData(@RequestBody PthaInfoSaveRequestDto pthaInfoSaveRequestDto)
      throws ContractException, TimeoutException, InterruptedException {
    logger.debug("========== saveWoutData START ==========");
    logger.debug("pthaInfoSaveRequestDto: {}",  pthaInfoSaveRequestDto.toString());
    
    List<PthaInfoSaveDto> pthaInfoList = pthaInfoSaveRequestDto.getData();
    JobRequestDto jobInfo = pthaInfoSaveRequestDto.getHead();

    pthaInfoList.forEach(pthaInfoDto -> {
      if(StringUtils.isEmpty(pthaInfoDto.getWork_dates()) || StringUtils.isEmpty(pthaInfoDto.getTolof_cd()) ||
         StringUtils.isEmpty(pthaInfoDto.getWork_no()) || StringUtils.isEmpty(pthaInfoDto.getVhcl_pros_no()) ) {
        logger.error("work_dates: {}, tolof_cd: {}, work_no: {}, vhcl_pros_no: {}", 
        pthaInfoDto.getWork_dates(), pthaInfoDto.getTolof_cd(), pthaInfoDto.getWork_no(), pthaInfoDto.getVhcl_pros_no());
        throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
      }
    });

    CommonResult CCResponse = pthaInfoSevice.savePthaInfo(jobInfo, pthaInfoList);
    return responseService.setResult(CCResponse);
  } 

}
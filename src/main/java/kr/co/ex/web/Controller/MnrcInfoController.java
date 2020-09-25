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
import kr.co.ex.service.MnrcInfoService;
import kr.co.ex.web.dto.request.JobRequestDto;
import kr.co.ex.web.dto.request.mnrcinfo.MnrcInfoSaveDto;
import kr.co.ex.web.dto.request.mnrcinfo.MnrcInfoSaveRequestDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import kr.co.ex.web.dto.response.common.ResponseResult;
import kr.co.ex.web.dto.response.common.ResponseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MnrcInfoController {
  private static final Logger logger = LoggerFactory.getLogger(MnrcInfoController.class);
  private final ResponseService responseService;
  private final MnrcInfoService mnrcInfoSevice;

  @PostMapping("/saveMnrcData")
  public ResponseResult saveMnrcData(@RequestBody MnrcInfoSaveRequestDto mnrcInfoSaveRequestDto)
      throws ContractException, TimeoutException, InterruptedException {
    logger.debug("========== saveMnrcData START ==========");
    logger.debug("mnrcInfoSaveRequestDto: {}",  mnrcInfoSaveRequestDto.toString());
    
    List<MnrcInfoSaveDto> mnrcInfoList = mnrcInfoSaveRequestDto.getData();
    JobRequestDto jobInfo = mnrcInfoSaveRequestDto.getHead();

    mnrcInfoList.forEach(mnrcInfoDto -> {
      if(StringUtils.isEmpty(mnrcInfoDto.getWork_dates()) || StringUtils.isEmpty(mnrcInfoDto.getTolof_cd()) ||
         StringUtils.isEmpty(mnrcInfoDto.getWork_no()) || StringUtils.isEmpty(mnrcInfoDto.getVhcl_pros_no()) ) {
        logger.error("work_dates: {}, tolof_cd: {}, work_no: {}, vhcl_pros_no: {}", 
        mnrcInfoDto.getWork_dates(), mnrcInfoDto.getTolof_cd(), mnrcInfoDto.getWork_no(), mnrcInfoDto.getVhcl_pros_no());
        throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
      }
    });

    CommonResult CCResponse = mnrcInfoSevice.saveMnrcInfo(jobInfo, mnrcInfoList);
    return responseService.setResult(CCResponse);
  } 

}
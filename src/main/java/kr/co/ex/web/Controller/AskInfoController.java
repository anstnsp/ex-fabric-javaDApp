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
import kr.co.ex.service.AskInfoService;
import kr.co.ex.web.dto.request.JobRequestDto;
import kr.co.ex.web.dto.request.askinfo.AskInfoSaveDto;
import kr.co.ex.web.dto.request.askinfo.AskInfoSaveRequestDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import kr.co.ex.web.dto.response.common.ResponseResult;
import kr.co.ex.web.dto.response.common.ResponseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AskInfoController {
  private static final Logger logger = LoggerFactory.getLogger(AskInfoController.class);
  private final ResponseService responseService;
  private final AskInfoService askInfoSevice;

  @PostMapping("/saveAskData")
  public ResponseResult saveAskData(@RequestBody AskInfoSaveRequestDto askInfoSaveRequestDto)
      throws ContractException, TimeoutException, InterruptedException {
    logger.debug("========== saveAskData START ==========");
    logger.debug("AskInfoSaveRequestDto: {}",  askInfoSaveRequestDto.toString());
    
    List<AskInfoSaveDto> askInfoList = askInfoSaveRequestDto.getData();
    JobRequestDto jobInfo = askInfoSaveRequestDto.getHead();

    askInfoList.forEach(askInfoDto -> {
      if(StringUtils.isEmpty(askInfoDto.getWork_dates()) || StringUtils.isEmpty(askInfoDto.getTolof_cd()) ||
         StringUtils.isEmpty(askInfoDto.getWork_no()) || StringUtils.isEmpty(askInfoDto.getVhcl_pros_no()) ) {
        logger.error("work_dates: {}, tolof_cd: {}, work_no: {}, vhcl_pros_no: {}", 
        askInfoDto.getWork_dates(), askInfoDto.getTolof_cd(), askInfoDto.getWork_no(), askInfoDto.getVhcl_pros_no());
        throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
      }
    });

    CommonResult CCResponse = askInfoSevice.saveAskInfo(jobInfo, askInfoList);
    return responseService.setResult(CCResponse);
  } 

}
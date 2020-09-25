package kr.co.ex.web.Controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;



import org.hyperledger.fabric.gateway.ContractException;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import kr.co.ex.exception.RequiredParamException;
import kr.co.ex.service.PassService;

import kr.co.ex.web.dto.request.JobRequestDto;

import kr.co.ex.web.dto.request.pass.PassSaveDto;
import kr.co.ex.web.dto.request.pass.PassSaveRequestDto;
import kr.co.ex.web.dto.request.pass.PassSearchRequestDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import kr.co.ex.web.dto.response.common.ResponseResult;
import kr.co.ex.web.dto.response.common.ResponseSearchResult;
import kr.co.ex.web.dto.response.common.ResponseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PassController {

  private static final Logger logger = LoggerFactory.getLogger(PassController.class);
  private final PassService passService;
  private final ResponseService responseService; 

  
  //통행원시정보저장(DM-DP-001001)
  @PostMapping("/savePassData")
  public ResponseResult savePassData(@RequestBody PassSaveRequestDto passSaveRequestDto)
      throws ContractException, TimeoutException, InterruptedException {
    logger.debug("========== savePassData START ==========");
    logger.debug("passSaveRequestDto: {}",  passSaveRequestDto.toString());
    
    List<PassSaveDto> passList = passSaveRequestDto.getData();
    JobRequestDto jobInfo = passSaveRequestDto.getHead();

    passList.forEach(passSaveDto -> {
      if(StringUtils.isEmpty(passSaveDto.getWork_dates()) || StringUtils.isEmpty(passSaveDto.getTolof_cd()) ||
         StringUtils.isEmpty(passSaveDto.getWork_no()) || StringUtils.isEmpty(passSaveDto.getVhcl_pros_no()) ){
        logger.error("work_dates: {}, tolof_cd: {}, work_no: {}, vhcl_pros_no: {}", passSaveDto.getWork_dates(), 
        passSaveDto.getTolof_cd(), passSaveDto.getWork_no(), passSaveDto.getVhcl_pros_no());
        throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
      }
    });

    CommonResult CCResponse = passService.savePassData(jobInfo, passList);
    return responseService.setResult(CCResponse);

  }

  //통행정보 조회(BS-DP-001001)
  @PostMapping("/searchPassInfo")
  public ResponseSearchResult searchPassInfo(@RequestBody PassSearchRequestDto passSearchRequestDto)
      throws ContractException, ParseException, IOException {
    logger.debug("========== searchPassInfo START ==========");
    logger.debug("passSearchRequestDto: {}", passSearchRequestDto); 
    
    if(StringUtils.isEmpty(passSearchRequestDto.getWork_dates_from()) || StringUtils.isEmpty(passSearchRequestDto.getWork_dates_to())){
      logger.error("work_dates_from: {}, work_dates_to: {}", passSearchRequestDto.getWork_dates_from(), passSearchRequestDto.getWork_dates_to());
      throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
    }
    
    Object searchData = passService.searchPassInfo(passSearchRequestDto);
    return responseService.getListResult(searchData);
    
  }

}

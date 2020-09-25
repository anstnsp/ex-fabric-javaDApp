package kr.co.ex.service;


import java.io.IOException;

import java.util.List;
import java.util.concurrent.TimeoutException;



import org.hyperledger.fabric.gateway.ContractException;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ex.common.Chaincode;
import kr.co.ex.common.ChaincodeFunction;
import kr.co.ex.common.Channel;
import kr.co.ex.domain.job.JobInfoRepository;

import kr.co.ex.util.FabricSDK;

import kr.co.ex.web.dto.request.JobRequestDto;

import kr.co.ex.web.dto.request.pass.PassSaveDto;
import kr.co.ex.web.dto.request.pass.PassSearchRequestDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(rollbackFor = {ContractException.class, TimeoutException.class, InterruptedException.class})
public class PassService {

  private static final Logger logger = LoggerFactory.getLogger(PassService.class);
  private final FabricSDK fabricSDK;
  private final JobInfoRepository jobInfoRepository;

  //통행원시정보저장
  public CommonResult savePassData(JobRequestDto jobInfo, List<PassSaveDto> passList)
      throws ContractException, TimeoutException, InterruptedException {
    logger.debug("========== savePassData START ==========");
    // 1.DB에 JOB_INFO 저장. 
    jobInfoRepository.save(jobInfo.toEntity());
    
    //2.블록체인에 저장. 
    CommonResult CCResponse = fabricSDK.invokePublicTrasaction(Channel.DM_CHANNEL, Chaincode.DM_CC,
    ChaincodeFunction.SET_PASSINFO, passList);
    //3.결과값 리턴. 
    return CCResponse; 

  }

  //통행정보 조회 
  public Object searchPassInfo(PassSearchRequestDto dto) throws ContractException, ParseException, IOException {
    logger.debug("========== searchPassInfo START ==========");
    Object result = fabricSDK.evaluateTransaction(Channel.DM_CHANNEL, Chaincode.DM_CC, ChaincodeFunction.GET_PASSINFOS, dto);
    return result; 

  }

}


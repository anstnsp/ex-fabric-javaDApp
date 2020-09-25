package kr.co.ex.service;


import java.util.List;
import java.util.concurrent.TimeoutException;
import org.springframework.transaction.annotation.Transactional;
import org.hyperledger.fabric.gateway.ContractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import kr.co.ex.common.Chaincode;
import kr.co.ex.common.ChaincodeFunction;
import kr.co.ex.common.Channel;

import kr.co.ex.domain.insinfo.InsInfoRepository;
import kr.co.ex.domain.job.JobInfoRepository;
import kr.co.ex.util.FabricSDK;
import kr.co.ex.web.dto.request.JobRequestDto;
import kr.co.ex.web.dto.request.invg.InvgSaveDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = { ContractException.class, TimeoutException.class, InterruptedException.class })
public class InvgService {

  private static final Logger logger = LoggerFactory.getLogger(InvgService.class);
  private final FabricSDK fabricSdk;
  private final JobInfoRepository jobInfoRepository;
  private final InsInfoRepository insInfoRepository;

  public CommonResult saveBusiInvgData(JobRequestDto jobInfo, List<InvgSaveDto> invgList)
      throws ContractException, TimeoutException, InterruptedException {
    logger.debug("========== saveBusiInvgData START ==========");

    //1.받은 데이터 디비에 저장.
    jobInfoRepository.save(jobInfo.toEntity());
    invgList.forEach(invgSaveDto -> insInfoRepository.save(invgSaveDto.toEntity()));

    //2.블록체인에 저장함.
    CommonResult CCResponse = fabricSdk.invokePublicTrasaction(Channel.DM_CHANNEL, Chaincode.DM_CC,
        ChaincodeFunction.SET_INVG, invgList);
    //3.결과값 리턴. 
    return CCResponse; 

  }

  public CommonResult saveInteInvgData(JobRequestDto jobInfo, List<InvgSaveDto> invgList)
      throws ContractException, TimeoutException, InterruptedException {
    logger.debug("========== saveInteInvgData START ==========");

    //1.받은 데이터 디비에 저장.
    jobInfoRepository.save(jobInfo.toEntity());
    invgList.forEach(invgSaveDto -> insInfoRepository.save(invgSaveDto.toEntity()));

    //2.블록체인에 저장함.
    CommonResult CCResponse = fabricSdk.invokePublicTrasaction(Channel.DM_CHANNEL, Chaincode.DM_CC, ChaincodeFunction.SET_INVG, invgList);
    //3.결과값 리턴. 
    return CCResponse; 

  }

}
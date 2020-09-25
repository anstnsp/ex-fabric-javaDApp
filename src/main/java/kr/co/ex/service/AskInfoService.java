package kr.co.ex.service;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.ContractException;
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
import kr.co.ex.web.dto.request.askinfo.AskInfoSaveDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = { ContractException.class, TimeoutException.class, InterruptedException.class })
public class AskInfoService {
  private static final Logger logger = LoggerFactory.getLogger(AskInfoService.class);
  private final FabricSDK fabricSdk;
  private final JobInfoRepository jobInfoRepository;

  
  public CommonResult saveAskInfo(JobRequestDto jobInfo, List<AskInfoSaveDto> askInfoList)
      throws ContractException, TimeoutException, InterruptedException {
    logger.debug("========== saveAskInfo START ==========");
    // 1.DB에 JOB_INFO 저장. 
    jobInfoRepository.save(jobInfo.toEntity());

    //2.블록체인에 저장. 
    CommonResult CCResponse = fabricSdk.invokePublicTrasaction(Channel.DM_CHANNEL, Chaincode.DM_CC,
    ChaincodeFunction.SAVE_ASKDATA, askInfoList);
    //3.결과값 리턴. 
    return CCResponse; 
  }

}
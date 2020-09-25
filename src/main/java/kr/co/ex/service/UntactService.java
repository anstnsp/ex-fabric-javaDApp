package kr.co.ex.service;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.ContractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.ex.common.Chaincode;
import kr.co.ex.common.ChaincodeFunction;
import kr.co.ex.common.Channel;
import kr.co.ex.util.FabricSDK;
import kr.co.ex.web.dto.request.untact.UntactRequestDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UntactService {
    
    private static final Logger logger = LoggerFactory.getLogger(UntactService.class);
    private final FabricSDK fabricSdk;

    public CommonResult saveUntactData(List<UntactRequestDto> untactRequestDto)
      throws ContractException, TimeoutException, InterruptedException {
    logger.debug("========== saveUntactData START ==========");

    //1.블록체인에 저장함.
    CommonResult CCResponse = fabricSdk.invokePublicTrasaction(Channel.DM_CHANNEL, Chaincode.DM_CC,
        ChaincodeFunction.SAVE_CIVIL, untactRequestDto);

    //2.결과값 리턴. 
    return CCResponse; 

  }
}
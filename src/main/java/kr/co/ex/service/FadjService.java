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
import kr.co.ex.util.FabricSDK;
import kr.co.ex.web.dto.request.fadj.FadjSearchResquestDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = { ContractException.class, TimeoutException.class, InterruptedException.class })
public class FadjService {

    private static final Logger logger = LoggerFactory.getLogger(FadjService.class);
    private final FabricSDK fabricSDK;

    public <T> CommonResult saveFadjData(List<T> fadjList, ChaincodeFunction chaincodeFunction)
            throws ContractException, TimeoutException, InterruptedException {
        logger.debug("========== saveFadjData START ==========");

        // 1.fadjList 데이터는 블록체인에 저장
        CommonResult CCResponse = fabricSDK.invokePublicTrasaction(Channel.DM_CHANNEL, Chaincode.DM_CC,
                chaincodeFunction, fadjList);

        // 2.결과값 리턴
        return CCResponse;
        
    }

    public Object searchFadjData(FadjSearchResquestDto dto, ChaincodeFunction function) throws IOException, ParseException, ContractException {
        logger.debug("========== searchFadjData START ==========");
        Object result = fabricSDK.evaluateTransaction(Channel.DM_CHANNEL, Chaincode.DM_CC, function, dto);
        return result; 
    }

}
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
import kr.co.ex.web.dto.request.unpaid.UnpaidDtlDto;
import kr.co.ex.web.dto.request.unpaid.UnpaidMasterDto;
import kr.co.ex.web.dto.request.unpaid.UnpaidsearchRequestDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = { ContractException.class, TimeoutException.class, InterruptedException.class })
public class UnpaidService {

    private static final Logger logger = LoggerFactory.getLogger(UnpaidService.class);
    private final FabricSDK fabricSDK;
    private final JobInfoRepository jobInfoRepository;

    public CommonResult saveUnpaidData(JobRequestDto jobInfo, List<UnpaidMasterDto> masterList,
            List<UnpaidDtlDto> dtlList) throws ContractException, TimeoutException, InterruptedException {
        logger.debug("========== saveUnpaidData START ==========");

        // 1. DB에 JOB_INFO 저장
        jobInfoRepository.save(jobInfo.toEntity());

        // 2. 채널1 체인코드에 data(master)를 보낸다.
        fabricSDK.invokePublicTrasaction(Channel.DM_CHANNEL, Chaincode.DM_CC,
                ChaincodeFunction.SET_UNPAID, masterList);
        // 3.채널2 체인코드에 data(dtl)를 보낸다.
        CommonResult CCResponse2 = fabricSDK.invokePrivPubTransaction(Channel.DH_CHANNEL, Chaincode.DH_CC,
                ChaincodeFunction.SET_UNPAID, "dtl", dtlList, "");
        return CCResponse2;

    }

    public Object searchUnpaidData(UnpaidsearchRequestDto dto) throws ContractException, IOException, ParseException {
        logger.debug("========== searchUnpaidData START ==========");
        Object result = fabricSDK.evaluateTransaction(Channel.DM_CHANNEL, Chaincode.DM_CC, ChaincodeFunction.GET_UNPAID, dto);
        return result; 
    }

}
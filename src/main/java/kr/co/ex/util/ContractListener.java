package kr.co.ex.util;

import java.io.IOException;
import java.util.function.Consumer;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractEvent;


import org.hyperledger.fabric.sdk.Peer;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.ex.common.Chaincode;
import kr.co.ex.common.ChaincodeFunction;
import kr.co.ex.common.Channel;
import kr.co.ex.config.HLFProps;
import kr.co.ex.domain.checkpoint.CheckPoint;
import kr.co.ex.web.dto.response.common.CommonResult;
import kr.co.ex.web.dto.response.common.ResponseResult;
import lombok.RequiredArgsConstructor;
  
@RequiredArgsConstructor
@Component
public class ContractListener { 
 
  private static final Logger logger = LoggerFactory.getLogger(ContractListener.class);
  private final HLFProps hlfProps; 

  @Autowired
  private FabricSDK fabricSDK;
  @Autowired
  private CheckpointerImpl cp; 
  @Autowired
  private RestTemplateUtil restTemplateUtil; 

  protected Consumer<ContractEvent> contractListener;
  
	public void registerContractListener() throws IOException {
		Contract contract = fabricSDK.getContract(Channel.DH_CHANNEL, Chaincode.DH_CC); // 하이퍼렛저 채널,체인코드에 연결
    logger.info("############### Register ContractListener ###############");
    if(contractListener != null) { 
      logger.info("이전에 등록된 리스너를 지움. :" + contractListener);
      contract.removeContractListener(contractListener);  
    }   

    contractListener = contract.addContractListener(cp.getBlockNumber(), listenerImpl, hlfProps.getEventName());
	}

  
	Consumer<ContractEvent> listenerImpl = contractEvent -> {    
		logger.debug("############### catched contractEvent ###############");
    
		Long blockNumber = contractEvent.getTransactionEvent().getBlockEvent().getBlockNumber();
		String txId = contractEvent.getTransactionEvent().  getTransactionID();
		boolean isValidTx = contractEvent.getTransactionEvent().isValid();
    Peer peer = contractEvent.getTransactionEvent().getPeer();   
    byte[] binaryPayload = contractEvent.getPayload().get(); 
    String payload = new String(binaryPayload);
    
		logger.debug("blockNumber: {}, txId: {}, txStatus: {}", blockNumber, txId, isValidTx);
    logger.debug("From peer : {}", peer);
    logger.debug("payload: {}", payload);
 
    //이미 받은 트랜잭션일 경우.
    if(cp.getTransactionIds(blockNumber).contains(txId)) {
      logger.debug("_onEvent skipped transaction: {}", txId);
      return;
    }
    
    CheckPoint checkPoint = CheckPoint.builder().blockNumber(blockNumber)
                                      .txId(txId).isValid(isValidTx).peer(peer.getName()).build(); 
    try {  
      //타당한 트랜잭션이 아닌경우.
      if(isValidTx == false) {
        logger.warn("txStatus is not VALID. blockNumber: {}, txId: {}, txStatus: {}", blockNumber, txId, isValidTx); 
        logger.warn("payload: {}", payload);
        cp.addRow(checkPoint);
        return;
      }           
      
      JsonArray jsonArray = (JsonArray) JsonParser.parseString(payload);  
      
      //여기 아래 부터가 정상적인 트랜잭션을 받은 경우... 로직수행 
      //1.업무WAS에 payload 그대로 보냄.(업무시스템에 납부상태 갱신 요청)
      // ResponseResult response = restTemplateUtil.post(payload);
      // if(response.getResult().getCode() == 9999) {
        //2.데이터통합? 호출. 
        // fabricSDK.evaluateTransaction(Channel.DH_CHANNEL, Chaincode.DH_CC, ChaincodeFunction.GET_RSTUNPAID, findKey)
        //2.와스에 대한 응답이 성공이면 체인코드(PDC)에 payload 그대로 호출. (미납정보 상태변경) 
        CommonResult CCResponse = fabricSDK.invokePrivPubTransaction(Channel.DH_CHANNEL, Chaincode.DH_CC, ChaincodeFunction.SAVE_RSTUNPAID, "dtl", jsonArray, "");
        if(CCResponse.getCode() == 9999)  cp.addRow(checkPoint); //체인코드의 응답이 성공이면 체크포인트 디비에 저장. 
      // }
      
    } catch(Exception e) {
      logger.error("fail to save checkpoint on offChain DB", e); 
    }
   
	};
 
}
package kr.co.ex.util;


import org.hyperledger.fabric.sdk.helper.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener; 
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Service;

import kr.co.ex.config.HLFProps;

@Service
public class AppService implements CommandLineRunner, ApplicationListener<ContextClosedEvent> {

  private static final Logger logger = LoggerFactory.getLogger(AppService.class);
  @Autowired
  private ContractListener contractListener; 
  @Autowired
  private HLFProps hlfProps; 
  @Autowired
  private FabricSDK fabricSDK; 

  @Override 
  public void onApplicationEvent(ContextClosedEvent event) {
    logger.info("##### START 서버종료이벤트 발생!!!! #####" );
    logger.info("서버 종료시 활동중인 스레드 갯수:"+java.lang.Thread.activeCount());
    logger.info("##### END 서버종료이벤트 발생!!!! #####");
  } 

  @Override 
  public void run(String... args) throws Exception {
    logger.info("##### START 서버 실행!!#####");  
    System.setProperty(Config.ORG_HYPERLEDGER_FABRIC_SDK_CONFIGURATION, hlfProps.getSettingConfing()); 
    fabricSDK.enrollAdmin();
    contractListener.registerContractListener();
    logger.info("서버 시작 시 활동중인 스레드 갯수:"+java.lang.Thread.activeCount());
    logger.info("##### END 서버 실행!! #####");  
  }

}



  
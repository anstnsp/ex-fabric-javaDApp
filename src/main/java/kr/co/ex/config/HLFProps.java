package kr.co.ex.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix ="hyperledger")
public class HLFProps {

  private String walletPath; 
  private String connectionConfig; 
  private String caIpPort; 
  private String caCertPath; 
  private String hostIp; 
  private String caEnrollId;
  private String caEnrollPw; 
  private String mspId; 
  private String checkpointPath;
  private String settingConfing; 
  private String eventName;
}


package kr.co.ex.util;





import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import kr.co.ex.web.dto.response.common.ResponseResult;

@Component
public class RestTemplateUtil {
  private final Logger logger = LoggerFactory.getLogger(RestTemplateUtil.class);
  @Autowired
  private RestTemplate restTemplate; 

  @Value("${restTemplate.url}")
  private String url;

  public ResponseResult post(Object body) {
    HttpHeaders headers = new HttpHeaders(); 
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Object> request = new HttpEntity<Object>(body, headers); 
    try {
      ResponseResult response = restTemplate.postForObject(url, request, ResponseResult.class);
      return response;
    } catch (Exception e) {
      logger.error("restTemplate 통신에러 :{}", e.getMessage());
      throw e;
    }
    
    
  }
}
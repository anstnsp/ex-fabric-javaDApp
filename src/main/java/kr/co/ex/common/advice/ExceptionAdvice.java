package kr.co.ex.common.advice;


import org.hyperledger.fabric.gateway.ContractException;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.co.ex.exception.CustomContractException;
import kr.co.ex.exception.ParamLengthException;
import kr.co.ex.exception.RequiredParamException;
import kr.co.ex.web.dto.response.common.CommonResult;
import kr.co.ex.web.dto.response.common.ResponseResult;
import kr.co.ex.web.dto.response.common.ResponseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

  private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
	private final ResponseService responseService;
	private final MessageSource messageSource; 

  //아래 예외를 제외한 모든 에러 
	@ExceptionHandler(Exception.class) 
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	protected ResponseResult handleException(Exception e) {
    logger.error("handleException", e);
    CommonResult failResult = responseService.getFailResult(Integer.valueOf(getMessage("unKnown.code")), getMessage("unKnown.msg"));
    return responseService.setResult(failResult);

	}

  //필수파라미터 에러 
  @ExceptionHandler({RequiredParamException.class}) 
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  protected ResponseResult handleRequiredParamException(RequiredParamException e) {
    logger.error("handleRequiredParamException", e);
    CommonResult failResult = responseService.getFailResult(Integer.valueOf(getMessage("requiredParam.code")), getMessage("requiredParam.msg"));
    return responseService.setResult(failResult);
  }

  //JSON파싱 에러 
  @ExceptionHandler({ParseException.class}) 
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  protected ResponseResult handleParseException(ParseException e) {
    logger.error("handleParseException", e);
    CommonResult failResult = responseService.getFailResult(Integer.valueOf(getMessage("noJson.code")), getMessage("noJson.msg"));
    return responseService.setResult(failResult);
  }

  /**
   * 체인코드 실패 시 ContractException 발생한다.
   * 체인코드에 정의되지 않은 에러를 받는 경우. 
   */
  @ExceptionHandler(ContractException.class)
  protected ResponseResult handleContractException(ContractException e) {
    logger.error("handleContractException", e); 
    logger.error("ContractException : {}", e.getMessage());
    CommonResult failResult = responseService.getFailResult(Integer.valueOf(getMessage("CCError.code")), getMessage("CCError.msg"));
    return responseService.setResult(failResult);
	}
  
  /**
   * 
   * 체인코드에서 정의한 에러 발생 시 ChaincodeException 
   * 체인코드에서 정의한 에러코드와 메세지를 그대로 리턴한다. 
   */
  @ExceptionHandler(CustomContractException.class)
  protected ResponseResult handleChaincodeException(CustomContractException e) {
    logger.error("handleChaincodeException", e); 
    CommonResult failResult = responseService.getFailResult(e.getCode(), e.getMessage());
    return responseService.setResult(failResult);
  }

  /**
   * 요청으로 받은 값의 길이가 맞지 않을 때 에러. 
   */
  @ExceptionHandler(ParamLengthException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  protected ResponseResult handleParamLengthException(ParamLengthException e) {
    logger.error("handleParamLengthException", e); 
    CommonResult failResult = responseService.getFailResult(Integer.valueOf(getMessage("lengthError.code")), getMessage("lengthError.msg"));
    return responseService.setResult(failResult);
  }
  // DuplicateKeyException

  /**
   * 데이터베이스 키 중복에러 
   */
  @ExceptionHandler(DuplicateKeyException.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  protected ResponseResult handleDuplicateKeyException(DuplicateKeyException e) {
    logger.error("handleDuplicateKeyException", e); 
    CommonResult failResult = responseService.getFailResult(Integer.valueOf(getMessage("duplicateKeyError.code")), getMessage("duplicateKeyError.msg"));
    return responseService.setResult(failResult);
  }

   //code정보에 해당하는 메시지를 조회.
   private String getMessage(String code) {
       return getMessage(code, null);
   }
   
   //code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회.
   private String getMessage(String code, Object[] args) {
       return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
   }

}

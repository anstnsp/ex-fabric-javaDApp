package kr.co.ex.exception;

public class RequiredParamException extends RuntimeException {
  
  private static final long serialVersionUID = -4185145928498692474L;

  public RequiredParamException(String message) {
    super(message); 
  }

  public RequiredParamException(String message, Throwable cause) {
    super(message, cause);
  }
  
}


package kr.co.ex.exception;

import lombok.Getter;

@Getter
public class CustomContractException extends RuntimeException {
  
  private static final long serialVersionUID = -1171788529158697085L;

  int code; 
  public CustomContractException(String message) {
    super(message);
  }
  public CustomContractException(int code, String message) {
    super(message); 
    this.code = code; 
  }
  
  public CustomContractException(String message, Throwable cause) {
    super(message, cause); 
  }

}
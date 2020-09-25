package kr.co.ex.exception;

public class NoWalletOrCertException extends RuntimeException {
  
  private static final long serialVersionUID = 4936769423708522644L;

  public NoWalletOrCertException(String message) {
    super(message);
  }

  public NoWalletOrCertException(String message, Throwable cause) {
    super(message, cause); 
  }
  
}
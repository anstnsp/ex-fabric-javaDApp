package kr.co.ex.exception;

public class ParamLengthException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ParamLengthException(String message) {
    super(message);
  }

  public ParamLengthException(String message, Throwable cause) {
    super(message, cause);
  }
}
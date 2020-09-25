package kr.co.ex.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Chaincode {
  DM_CC(0, "dm-cc"),
  DH_CC(1, "dh-cc");
  
  private final int index;
  private final String value;
}
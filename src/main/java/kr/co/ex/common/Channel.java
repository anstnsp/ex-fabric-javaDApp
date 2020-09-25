package kr.co.ex.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Channel {
  DM_CHANNEL(0, "dm-channel"),
  DH_CHANNEL(1, "dh-channel");
  
  private final int index;
  private final String value; 
  
}
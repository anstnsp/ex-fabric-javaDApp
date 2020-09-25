package kr.co.ex.web.dto.request.pass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PassSearchRequestDto {
  
  private String work_dates_from;
  private String work_dates_to;
  private String wout_hoinst_cd;
  private String mnrc_clss_cd; 

  // private String page_size;
  // private String book_mark;
}
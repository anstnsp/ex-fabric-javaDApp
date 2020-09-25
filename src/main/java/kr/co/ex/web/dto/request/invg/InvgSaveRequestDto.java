package kr.co.ex.web.dto.request.invg;

import java.util.List;

import kr.co.ex.web.dto.request.JobRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class InvgSaveRequestDto {
  private JobRequestDto head;
  private List<InvgSaveDto> data; 
}
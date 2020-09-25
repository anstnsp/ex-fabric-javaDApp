package kr.co.ex.web.dto.request.askinfo;

import java.util.List;

import kr.co.ex.web.dto.request.JobRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AskInfoSaveRequestDto {
  private JobRequestDto head;
  private List<AskInfoSaveDto> data; 
}
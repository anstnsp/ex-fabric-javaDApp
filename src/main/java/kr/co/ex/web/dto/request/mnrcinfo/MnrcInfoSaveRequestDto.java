package kr.co.ex.web.dto.request.mnrcinfo;

import java.util.List;

import kr.co.ex.web.dto.request.JobRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MnrcInfoSaveRequestDto {
  private JobRequestDto head;
  private List<MnrcInfoSaveDto> data; 
}
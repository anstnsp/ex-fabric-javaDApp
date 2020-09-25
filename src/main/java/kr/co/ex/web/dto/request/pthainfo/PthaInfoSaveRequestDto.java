package kr.co.ex.web.dto.request.pthainfo;

import java.util.List;

import kr.co.ex.web.dto.request.JobRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PthaInfoSaveRequestDto {
  private JobRequestDto head;
  private List<PthaInfoSaveDto> data; 
}
package kr.co.ex.web.dto.request.unpaid;

import kr.co.ex.web.dto.request.JobRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UnpaidRequestDto {

    private JobRequestDto head;
    private UnpaidDataDto data; 
    
}
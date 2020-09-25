package kr.co.ex.web.dto.request.unpaid;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UnpaidDataDto {
    
    private List<UnpaidMasterDto> master;
    private List<UnpaidDtlDto> dtl;

}
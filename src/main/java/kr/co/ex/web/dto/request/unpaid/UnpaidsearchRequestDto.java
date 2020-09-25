package kr.co.ex.web.dto.request.unpaid;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UnpaidsearchRequestDto {

    private String tolof_cd;
    private String work_dates_from;
    private String work_dates_to;
    private String ntpd_amt;
    
    // private String page_size;
    // private String book_mark;
}
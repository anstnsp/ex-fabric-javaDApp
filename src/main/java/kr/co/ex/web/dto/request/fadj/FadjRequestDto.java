package kr.co.ex.web.dto.request.fadj;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FadjRequestDto<T>{
    private List<T> data;
}
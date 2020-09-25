package kr.co.ex.web.Controller;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.ContractException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ex.exception.RequiredParamException;
import kr.co.ex.service.UntactService;
import kr.co.ex.web.dto.request.untact.UntactRequestDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UntactController {

    private static final Logger logger = LoggerFactory.getLogger(UntactController.class);
    private final UntactService untactsevice;

    // 비대면 신청
    @PostMapping("/saveCivilData")
    public CommonResult saveCivilData(@RequestBody List<UntactRequestDto> untactList)
            throws ContractException, TimeoutException, InterruptedException {
        logger.debug("========== saveCivilData START ==========");
        logger.debug("untactRequestDto : {}", untactList.toString());

        //필수값 조회
        untactList.forEach(untactSaveDto -> {
            if(StringUtils.isEmpty(untactSaveDto.getVhno()) || StringUtils.isEmpty(untactSaveDto.getCivil_form_type_cd())){
                logger.error("vhno: {}, civil_form_type_cd: {}", 
                untactSaveDto.getVhno(), untactSaveDto.getCivil_form_type_cd());
                throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
            }
        });

        CommonResult CCResponse = untactsevice.saveUntactData(untactList);
        return CCResponse;

    }


}
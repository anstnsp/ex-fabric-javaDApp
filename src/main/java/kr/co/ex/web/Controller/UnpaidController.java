package kr.co.ex.web.Controller;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.TimeoutException;
import org.hyperledger.fabric.gateway.ContractException;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import kr.co.ex.exception.RequiredParamException;
import kr.co.ex.service.UnpaidService;
import kr.co.ex.web.dto.request.JobRequestDto;
import kr.co.ex.web.dto.request.unpaid.UnpaidDtlDto;
import kr.co.ex.web.dto.request.unpaid.UnpaidMasterDto;
import kr.co.ex.web.dto.request.unpaid.UnpaidRequestDto;
import kr.co.ex.web.dto.request.unpaid.UnpaidsearchRequestDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import kr.co.ex.web.dto.response.common.ResponseResult;
import kr.co.ex.web.dto.response.common.ResponseSearchResult;
import kr.co.ex.web.dto.response.common.ResponseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UnpaidController {

    private static final Logger logger = LoggerFactory.getLogger(UnpaidController.class);
    private final UnpaidService unpaidService;
    private final ResponseService responseService;

    // 미납정보저장(DM-DP-004001)
    @PostMapping("/saveNtpdData")
    public ResponseResult saveNtpdData(@RequestBody UnpaidRequestDto unpaidRequestDto)
            throws ContractException, TimeoutException, InterruptedException {
        logger.debug("========== saveNtpdData START ==========");
        logger.debug("unpaidRequestDto : {}", unpaidRequestDto.toString());

        // head(jobinfo) , data(master,dtl)
        JobRequestDto jobInfo = unpaidRequestDto.getHead();
        List<UnpaidMasterDto> masterList = unpaidRequestDto.getData().getMaster();
        List<UnpaidDtlDto> dtlList = unpaidRequestDto.getData().getDtl();

        // Channel1(도공-민자)에 들어갈 데이터 필수값 체크
        masterList.forEach(unpaidMasterDto -> {
            if (StringUtils.isEmpty(unpaidMasterDto.getWork_dates())
                    || StringUtils.isEmpty(unpaidMasterDto.getTolof_cd())
                    || StringUtils.isEmpty(unpaidMasterDto.getWork_no())
                    || StringUtils.isEmpty(unpaidMasterDto.getVhcl_pros_no())) {
                logger.error("work_data: {}, tolof_cd: {}, work_no: {}, vhcl_pros: {}", unpaidMasterDto.getWork_dates(),
                        unpaidMasterDto.getTolof_cd(), unpaidMasterDto.getWork_no(), unpaidMasterDto.getVhcl_pros_no());
                throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
            }
        });

        // Channel2(도공-하나)에 들어갈 데이터 필수값 체크
        dtlList.forEach(unpaidDtlDto -> {
            if (StringUtils.isEmpty(unpaidDtlDto.getWork_dates()) || StringUtils.isEmpty(unpaidDtlDto.getTolof_cd())
                    || StringUtils.isEmpty(unpaidDtlDto.getWork_no())
                    || StringUtils.isEmpty(unpaidDtlDto.getVhcl_pros_no())
                    || StringUtils.isEmpty(unpaidDtlDto.getEcard_mnrc_clss_cd())
                    || StringUtils.isEmpty(unpaidDtlDto.getHoinst_cd())) {
                logger.error(
                        "work_data: {}, tolof_cd: {}, work_no: {}, vhcl_pros: {}, ecard_mnrc_clss_cd: {}, hoinst_cd: {}",
                        unpaidDtlDto.getWork_dates(), unpaidDtlDto.getTolof_cd(), unpaidDtlDto.getWork_no(),
                        unpaidDtlDto.getVhcl_pros_no(), unpaidDtlDto.getEcard_mnrc_clss_cd(),
                        unpaidDtlDto.getHoinst_cd());
                throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
            }
        });

        CommonResult CCResponse = unpaidService.saveUnpaidData(jobInfo, masterList, dtlList);
        return responseService.setResult(CCResponse);
    }

    // 미납차량 관리대장
    @PostMapping("searchNtpdInfo")
    public ResponseSearchResult searchNtpdInfo(@RequestBody UnpaidsearchRequestDto unpaidsearchRequestDto)
            throws ContractException, IOException, ParseException {
        logger.debug("========== searchNtpdInfo START ==========");
        logger.debug("unpaidsearchRequestDto: {}", unpaidsearchRequestDto.toString());

        //필수값 조회
        if(StringUtils.isEmpty(unpaidsearchRequestDto.getNtpd_amt()) || unpaidsearchRequestDto.getTolof_cd() == null || 
          StringUtils.isEmpty(unpaidsearchRequestDto.getWork_dates_from()) || StringUtils.isEmpty(unpaidsearchRequestDto.getWork_dates_to()) ){
            throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
        }

        Object searchData = unpaidService.searchUnpaidData(unpaidsearchRequestDto);
        return responseService.getListResult(searchData);
    
    }

}
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

import kr.co.ex.common.ChaincodeFunction;
import kr.co.ex.exception.RequiredParamException;
import kr.co.ex.service.FadjService;
import kr.co.ex.web.dto.request.fadj.BondRefundSaveDto;
import kr.co.ex.web.dto.request.fadj.BondUnpaidTollsSaveDto;
import kr.co.ex.web.dto.request.fadj.FadjRequestDto;
import kr.co.ex.web.dto.request.fadj.FadjSearchResquestDto;
import kr.co.ex.web.dto.request.fadj.IncomeUnpaidTollsSaveDto;
import kr.co.ex.web.dto.request.fadj.LinkedDiscountSaveDto;
import kr.co.ex.web.dto.request.fadj.MonthCalcuateSaveDto;
import kr.co.ex.web.dto.request.fadj.PayablesUnpaidTollsSaveDto;
import kr.co.ex.web.dto.response.common.CommonResult;
import kr.co.ex.web.dto.response.common.ResponseResult;
import kr.co.ex.web.dto.response.common.ResponseSearchResult;
import kr.co.ex.web.dto.response.common.ResponseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class FadjController {

    private static final Logger logger = LoggerFactory.getLogger(FadjController.class);
    private final FadjService fadjService;
    private final ResponseService responseService;

    // 월정산집계 정보 전송(BS-DP-005001)
    @PostMapping("saveFadjSumData")
    public ResponseResult saveFadjSumData(@RequestBody FadjRequestDto<MonthCalcuateSaveDto> fadjRequestDto)
            throws ContractException, TimeoutException, InterruptedException {
        logger.debug("========== saveFadjSumData START ==========");
        logger.debug("invgSaveRequestDto: {}", fadjRequestDto.toString());

        // data 분리
        List<MonthCalcuateSaveDto> fadjList = fadjRequestDto.getData();

        // 필수값 체크
        fadjList.forEach(fadjMstSaveDto -> {
            if (StringUtils.isEmpty(fadjMstSaveDto.getFadj_yyyymm())) {
                logger.error("fadj_yyyy_mm: {}", fadjMstSaveDto.getFadj_yyyymm());
                throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
            }
        });
        CommonResult CCResponse = fadjService.saveFadjData(fadjList, ChaincodeFunction.SET_SUMMST);
        return responseService.setResult(CCResponse);
    }

    // 채권(도로공사 수입금) 미납통행료 정보 전송(BS-DP-005002)
    @PostMapping("saveFadjSumDtlData01")
    public ResponseResult saveFadjSumDtlData01(@RequestBody FadjRequestDto<BondUnpaidTollsSaveDto> fadjRequestDto)
            throws ContractException, TimeoutException, InterruptedException {
        logger.debug("========== saveFadjSumDtlData01 START ==========");
        logger.debug("invgSaveRequestDto: {}", fadjRequestDto.toString());

        // data 분리
        List<BondUnpaidTollsSaveDto> fadjList = fadjRequestDto.getData();

        // 필수값 체크
        fadjList.forEach(fadjDtl01SaveDto -> {
            if (StringUtils.isEmpty(fadjDtl01SaveDto.getFadj_yyyymm())
                    || StringUtils.isEmpty(fadjDtl01SaveDto.getTolof_cd())) {
                logger.error("fadj_yyyy_mm: {}, tolof_cd: {}", fadjDtl01SaveDto.getFadj_yyyymm(),
                        fadjDtl01SaveDto.getTolof_cd());
                throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
            }
        });
        CommonResult CCResponse = fadjService.saveFadjData(fadjList, ChaincodeFunction.SET_SUMDTL01);
        return responseService.setResult(CCResponse);
    }

    // 채권(도로공사 수입금) 환불금 정보 전송(BS-DP-005003)
    @PostMapping("saveFadjSumDtlData02")
    public ResponseResult saveFadjSumDtlData02(@RequestBody FadjRequestDto<BondRefundSaveDto> fadjRequestDto)
            throws ContractException, TimeoutException, InterruptedException {
        logger.debug("========== saveFadjSumDtlData02 START ==========");
        logger.debug("invgSaveRequestDto: {}", fadjRequestDto.toString());

        // data 분리
        List<BondRefundSaveDto> fadjList = fadjRequestDto.getData();

        // 필수값 체크
        fadjList.forEach(fadjDtl02SaveDto -> {
            if (StringUtils.isEmpty(fadjDtl02SaveDto.getFadj_yyyymm())
                    || StringUtils.isEmpty(fadjDtl02SaveDto.getHdqr_cd())) {
                logger.error("fadj_yyyy_mm: {}, hdqr_cd: {}", fadjDtl02SaveDto.getFadj_yyyymm(),
                        fadjDtl02SaveDto.getHdqr_cd());
                throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
            }
        });
        CommonResult CCResponse = fadjService.saveFadjData(fadjList, ChaincodeFunction.SET_SUMDTL02);
        return responseService.setResult(CCResponse);
    }

    // 대구부산 수입금 미납통행료: 신용카드 수수료 차장_총금액 정보 전송(BS-DP-005004)
    @PostMapping("saveFadjSumDtlData03")
    public ResponseResult saveFadjSumDtlData03(@RequestBody FadjRequestDto<IncomeUnpaidTollsSaveDto> fadjRequestDto)
            throws ContractException, TimeoutException, InterruptedException {
        logger.debug("========== saveFadjSumDtlData03 START ==========");
        logger.debug("invgSaveRequestDto: {}", fadjRequestDto.toString());

        // data 분리
        List<IncomeUnpaidTollsSaveDto> fadjList = fadjRequestDto.getData();

        // 필수값 체크
        fadjList.forEach(fadjDtl03SaveDto -> {
            if (StringUtils.isEmpty(fadjDtl03SaveDto.getFadj_yyyymm())
                    || StringUtils.isEmpty(fadjDtl03SaveDto.getHdqr_cd())) {
                logger.error("fadj_yyyy_mm: {}, hdqr_cd: {}", fadjDtl03SaveDto.getFadj_yyyymm(),
                        fadjDtl03SaveDto.getHdqr_cd());
                throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
            }
        });
        CommonResult CCResponse = fadjService.saveFadjData(fadjList, ChaincodeFunction.SET_SUMDTL03);
        return responseService.setResult(CCResponse);
    }

    // 채 무(대구부산 수입금)_환불금 정보 전송(BS-DP-005005)
    @PostMapping("saveFadjSumDtlData04")
    public ResponseResult saveFadjSumDtlData04(@RequestBody FadjRequestDto<PayablesUnpaidTollsSaveDto> fadjRequestDto)
            throws ContractException, TimeoutException, InterruptedException {
        logger.debug("========== saveFadjSumDtlData04 START ==========");
        logger.debug("invgSaveRequestDto: {}", fadjRequestDto.toString());

        // data 분리
        List<PayablesUnpaidTollsSaveDto> fadjList = fadjRequestDto.getData();

        // 필수값 체크
        fadjList.forEach(fadjDtl04SaveDto -> {
            if (StringUtils.isEmpty(fadjDtl04SaveDto.getFadj_yyyymm())
                    || StringUtils.isEmpty(fadjDtl04SaveDto.getTolof_cd())) {
                logger.error("fadj_yyyy_mm: {}, tolof_cd: {}", fadjDtl04SaveDto.getFadj_yyyymm(),
                        fadjDtl04SaveDto.getTolof_cd());
                throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
            }
        });
        CommonResult CCResponse = fadjService.saveFadjData(fadjList, ChaincodeFunction.SET_SUMDTL04);
        return responseService.setResult(CCResponse);
    }

    // 연계할인 통행료 정보 전송(BS-DP-005006)
    @PostMapping("saveFadjSumDtlData05")
    public ResponseResult saveFadjSumDtlData05(@RequestBody FadjRequestDto<LinkedDiscountSaveDto> fadjRequestDto)
            throws ContractException, TimeoutException, InterruptedException {
        logger.debug("========== saveFadjSumDtlData05 START ==========");
        logger.debug("invgSaveRequestDto: {}", fadjRequestDto.toString());

        // data 분리
        List<LinkedDiscountSaveDto> fadjList = fadjRequestDto.getData();

        // 필수값 체크
        fadjList.forEach(fadjDtl05SaveDto -> {
            if (StringUtils.isEmpty(fadjDtl05SaveDto.getFadj_yyyymm())
                    || StringUtils.isEmpty(fadjDtl05SaveDto.getPaym_kind_cd())) {
                logger.error("fadj_yyyy_mm: {}, paym_kind_cd: {}", fadjDtl05SaveDto.getFadj_yyyymm(),
                        fadjDtl05SaveDto.getPaym_kind_cd());
                throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
            }
        });
        CommonResult CCResponse = fadjService.saveFadjData(fadjList, ChaincodeFunction.SET_SUMDTL05);
        return responseService.setResult(CCResponse);
    }


    // 월정산 집계 결과 조회(BS-DP-006001)
    @PostMapping("/searchFadjSumInfo")
    public ResponseSearchResult searchFadjSumInfo(@RequestBody FadjSearchResquestDto fabjSearchResquestDto)
            throws ContractException, IOException, ParseException {
        logger.debug("========== searchFadjSumInfo START ==========");
        logger.debug("fabjSearchResquestDto: {}", fabjSearchResquestDto.toString());

        //필수값 체크
        if(StringUtils.isEmpty(fabjSearchResquestDto.getFadj_yyyymm())){
            logger.error("fadj_yyyymm:{}", fabjSearchResquestDto.getFadj_yyyymm()); 
            throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
        }

        //블록체인 조회
        Object searchData = fadjService.searchFadjData(fabjSearchResquestDto, ChaincodeFunction.GET_SUMMST);
        return responseService.getListResult(searchData);
    }

    // 도로공사 미납통행료 수입금 조회(BS-DP-006002)
    @PostMapping("/searchFadjSumDtlInfo01")
    public ResponseSearchResult searchFadjSumDtlInfo01(@RequestBody FadjSearchResquestDto fabjSearchResquestDto)
            throws ContractException, IOException, ParseException {
        logger.debug("========== searchFadjSumDtlInfo01 START ==========");
        logger.debug("fabjSearchResquestDto: {}", fabjSearchResquestDto.toString());

        //필수값 체크
        if(StringUtils.isEmpty(fabjSearchResquestDto.getFadj_yyyymm())){
            logger.error("fadj_yyyymm:{}", fabjSearchResquestDto.getFadj_yyyymm()); 
            throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
        }

        //블록체인 조회
        Object searchData = fadjService.searchFadjData(fabjSearchResquestDto, ChaincodeFunction.GET_SUMDTL01);
        return responseService.getListResult(searchData);
    }

    // 도로공사 환불금 수입금 조회(BS-DP-006003)
    @PostMapping("/searchFadjSumDtlInfo02")
    public ResponseSearchResult searchFadjSumDtlInfo02(@RequestBody FadjSearchResquestDto fabjSearchResquestDto)
            throws ContractException, IOException, ParseException {
        logger.debug("========== searchFadjSumDtlInfo02 START ==========");
        logger.debug("fabjSearchResquestDto: {}", fabjSearchResquestDto.toString());

        //필수값 체크
        if(StringUtils.isEmpty(fabjSearchResquestDto.getFadj_yyyymm())){
            logger.error("fadj_yyyymm:{}", fabjSearchResquestDto.getFadj_yyyymm()); 
            throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
        }

        //블록체인 조회
        Object searchData = fadjService.searchFadjData(fabjSearchResquestDto, ChaincodeFunction.GET_SUMDTL02);
        return responseService.getListResult(searchData);
    }

    // 신대구부산고속도로 미납통행료 수입금 조회(BS-DP-006004)
    @PostMapping("/searchFadjSumDtlInfo03")
    public ResponseSearchResult searchFadjSumDtlInfo03(@RequestBody FadjSearchResquestDto fabjSearchResquestDto)
            throws ContractException, IOException, ParseException {
        logger.debug("========== searchFadjSumDtlInfo03 START ==========");
        logger.debug("fabjSearchResquestDto: {}", fabjSearchResquestDto.toString());

        //필수값 체크
        if(StringUtils.isEmpty(fabjSearchResquestDto.getFadj_yyyymm())){
            logger.error("fadj_yyyymm:{}", fabjSearchResquestDto.getFadj_yyyymm()); 
            throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
        }

        //블록체인 조회
        Object searchData = fadjService.searchFadjData(fabjSearchResquestDto, ChaincodeFunction.GET_SUMDTL03);
        return responseService.getListResult(searchData);
    }

    // 신대구부산고속도로 환불금 수입금 조회(BS-DP-006005)
    @PostMapping("/searchFadjSumDtlInfo04")
    public ResponseSearchResult searchFadjSumDtlInfo04(@RequestBody FadjSearchResquestDto fabjSearchResquestDto)
            throws ContractException, IOException, ParseException {
        logger.debug("========== searchFadjSumDtlInfo04 START ==========");
        logger.debug("fabjSearchResquestDto: {}", fabjSearchResquestDto.toString());

        //필수값 체크
        if(StringUtils.isEmpty(fabjSearchResquestDto.getFadj_yyyymm())){
            logger.error("fadj_yyyymm:{}", fabjSearchResquestDto.getFadj_yyyymm()); 
            throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
        }

        //블록체인 조회
        Object searchData = fadjService.searchFadjData(fabjSearchResquestDto, ChaincodeFunction.GET_SUMDTL04);
        return responseService.getListResult(searchData);
    }

    // 연계할인 통행료 조회(BS-DP-006006)
    @PostMapping("/searchFadjSumDtlInfo05")
    public ResponseSearchResult searchFadjSumDtlInfo05(@RequestBody FadjSearchResquestDto fabjSearchResquestDto)
            throws ContractException, IOException, ParseException {
        logger.debug("========== searchFadjSumDtlInfo05 START ==========");
        logger.debug("fabjSearchResquestDto: {}", fabjSearchResquestDto.toString());

        //필수값 체크
        if(StringUtils.isEmpty(fabjSearchResquestDto.getFadj_yyyymm())){
            logger.error("fadj_yyyymm:{}", fabjSearchResquestDto.getFadj_yyyymm()); 
            throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
        }

        //블록체인 조회
        Object searchData = fadjService.searchFadjData(fabjSearchResquestDto, ChaincodeFunction.GET_SUMDTL05);
        return responseService.getListResult(searchData);
    }

}
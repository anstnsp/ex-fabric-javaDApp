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

import kr.co.ex.domain.insinfo.InsInfo;
import kr.co.ex.domain.insinfo.InsInfoRepository;
import kr.co.ex.exception.CustomContractException;
import kr.co.ex.exception.ParamLengthException;
import kr.co.ex.exception.RequiredParamException;
import kr.co.ex.service.InvgService;
import kr.co.ex.web.dto.request.JobRequestDto;
import kr.co.ex.web.dto.request.invg.InvgSaveDto;
import kr.co.ex.web.dto.request.invg.InvgSaveRequestDto;
import kr.co.ex.web.dto.request.invg.InvgSearchRequestDto;

import kr.co.ex.web.dto.response.common.CommonResult;
import kr.co.ex.web.dto.response.common.ResponseResult;
import kr.co.ex.web.dto.response.common.ResponseSearchResult;
import kr.co.ex.web.dto.response.common.ResponseService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class InvgController {

  private static final Logger logger = LoggerFactory.getLogger(InvgController.class);
  private final InvgService invgService;
  private final InsInfoRepository insInfoRepository; 
  private final ResponseService responseService; 

  //영업심사정보 저장(DM-DP-002001). 
  @PostMapping("/saveInvg01")
  public ResponseResult busiInvgSave(@RequestBody InvgSaveRequestDto invgSaveRequestDto)
      throws ContractException, TimeoutException, InterruptedException, CustomContractException {
    logger.debug("========== busiInvgSave START ==========");
    logger.debug("invgSaveRequestDto: {}", invgSaveRequestDto.toString());

    List<InvgSaveDto> invgList = invgSaveRequestDto.getData();
    JobRequestDto jobInfo = invgSaveRequestDto.getHead();

    //필수 값 체크. 
    invgList.forEach(invgSaveDto -> {
      if(StringUtils.isEmpty(invgSaveDto.getWork_dates()) || StringUtils.isEmpty(invgSaveDto.getTolof_cd()) ||
         StringUtils.isEmpty(invgSaveDto.getWork_no()) || StringUtils.isEmpty(invgSaveDto.getVhcl_pros_no()) ||
         StringUtils.isEmpty(invgSaveDto.getOcrn_tolof_cd()) || StringUtils.isEmpty(invgSaveDto.getPros_tord()) || StringUtils.isEmpty(invgSaveDto.getVio_type())){
        logger.error("work_dates: {}, tolof_cd: {}, work_no: {}, vhcl_pros_no: {}, ocrn_tolof_cd: {}, pros_tord: {}, vio_type: {}", invgSaveDto.getWork_dates(), invgSaveDto.getTolof_cd(), 
        invgSaveDto.getWork_no(), invgSaveDto.getVhcl_pros_no(), invgSaveDto.getOcrn_tolof_cd(), invgSaveDto.getPros_tord(), invgSaveDto.getVio_type());
        throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
      }

      //필드 길이 체크.
      if( (invgSaveDto.getClsn_yn() != null && invgSaveDto.getClsn_yn().length() != 1) ||
          (invgSaveDto.getCrgw_systm_clss_cd() != null && invgSaveDto.getCrgw_systm_clss_cd().length() != 1) ||
          (invgSaveDto.getFrkncr_cd() != null && invgSaveDto.getFrkncr_cd().length() != 1) ||
          (invgSaveDto.getHipas_dcex_cd() != null && invgSaveDto.getHipas_dcex_cd().length() != 1) ||
          (invgSaveDto.getInvg_pros_cd() != null && invgSaveDto.getInvg_pros_cd().length() != 1) ||
          (invgSaveDto.getNtpd_chng_yn() != null &&  invgSaveDto.getNtpd_chng_yn().length() > 2) ||
          (invgSaveDto.getType_invg_cd() != null && invgSaveDto.getType_invg_cd().length() != 1) ||
          (invgSaveDto.getVhno_chng_yn() != null && invgSaveDto.getVhno_chng_yn().length() != 1) ||
          (invgSaveDto.getVio_type() != null && invgSaveDto.getVio_type().length() > 2) ||
          (invgSaveDto.getWoutp_tolof_cd() != null && invgSaveDto.getWoutp_tolof_cd().length() > 4) ||
          (invgSaveDto.getWout_toll_pros_type_cd() != null && invgSaveDto.getWout_toll_pros_type_cd().length() != 1)
      ) {
        throw new ParamLengthException("파라미터의 길이가 맞지 않습니다.");
      }

    });
    
    CommonResult CCResponse = invgService.saveBusiInvgData(jobInfo, invgList); 
    return responseService.setResult(CCResponse);
  }

    //통합심사정보 저장(DM-DP-003001). 
    @PostMapping("/saveInvg02")
    public ResponseResult InteInvgSave(@RequestBody InvgSaveRequestDto invgSaveRequestDto)
        throws ContractException, TimeoutException, InterruptedException, CustomContractException {
      logger.debug("========== InteInvgSave START ==========");
      logger.debug("invgSaveRequestDto: {}", invgSaveRequestDto.toString());
  
      List<InvgSaveDto> invgList = invgSaveRequestDto.getData();
      JobRequestDto jobInfo = invgSaveRequestDto.getHead();
      
      invgList.forEach(invgSaveDto -> {
        if(StringUtils.isEmpty(invgSaveDto.getWork_dates()) || StringUtils.isEmpty(invgSaveDto.getTolof_cd()) ||
           StringUtils.isEmpty(invgSaveDto.getWork_no()) || StringUtils.isEmpty(invgSaveDto.getVhcl_pros_no()) || 
           StringUtils.isEmpty(invgSaveDto.getOcrn_tolof_cd()) || StringUtils.isEmpty(invgSaveDto.getPros_tord()) || StringUtils.isEmpty(invgSaveDto.getVio_type()) ) {
            logger.error("work_dates: {}, tolof_cd: {}, work_no: {}, vhcl_pros_no: {}, ocrn_tolof_cd: {}, pros_tord: {}, vio_type: {}", invgSaveDto.getWork_dates(), invgSaveDto.getTolof_cd(), 
            invgSaveDto.getWork_no(), invgSaveDto.getVhcl_pros_no(), invgSaveDto.getOcrn_tolof_cd(), invgSaveDto.getPros_tord(), invgSaveDto.getVio_type());
            throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
          }
  
      // 필드 길이 체크.
      if( (invgSaveDto.getClsn_yn() != null && invgSaveDto.getClsn_yn().length() != 1) ||
          (invgSaveDto.getCrgw_systm_clss_cd() != null && invgSaveDto.getCrgw_systm_clss_cd().length() != 1) ||
          (invgSaveDto.getFrkncr_cd() != null && invgSaveDto.getFrkncr_cd().length() != 1) ||
          (invgSaveDto.getHipas_dcex_cd() != null && invgSaveDto.getHipas_dcex_cd().length() != 1) ||
          (invgSaveDto.getInvg_pros_cd() != null && invgSaveDto.getInvg_pros_cd().length() != 1) ||
          (invgSaveDto.getNtpd_chng_yn() != null &&  invgSaveDto.getNtpd_chng_yn().length() > 2) ||
          (invgSaveDto.getType_invg_cd() != null && invgSaveDto.getType_invg_cd().length() != 1) ||
          (invgSaveDto.getVhno_chng_yn() != null && invgSaveDto.getVhno_chng_yn().length() != 1) ||
          (invgSaveDto.getVio_type() != null && invgSaveDto.getVio_type().length() > 2) ||
          (invgSaveDto.getWoutp_tolof_cd() != null && invgSaveDto.getWoutp_tolof_cd().length() > 4) ||
          (invgSaveDto.getWout_toll_pros_type_cd() != null && invgSaveDto.getWout_toll_pros_type_cd().length() != 1)
      ) {
        throw new ParamLengthException("파라미터의 길이가 맞지 않습니다.");
      }
      });
      
      CommonResult CCResponse = invgService.saveInteInvgData(jobInfo, invgList); 
      return responseService.setResult(CCResponse);
    }

    //차량번호 변경이력 조회 - 필수 뺀 조건(Y, null)
    //미납금액 변경이력 조회 - 필수 뺀 조건(NULL, Y)
    //심사 이력 조회        - 필수 뺀 조건(NULL, NULL)
    @PostMapping("/searchChangeInfo")
    public ResponseSearchResult searchChangeInfo(@RequestBody InvgSearchRequestDto invgSearchRequestDto) {
      logger.debug("========== searchChangeInfo START ==========");
      logger.debug("invgSearchRequestDto: {}", invgSearchRequestDto.toString());

      if(StringUtils.isEmpty(invgSearchRequestDto.getWork_dates()) || StringUtils.isEmpty(invgSearchRequestDto.getTolof_cd()) ||
         StringUtils.isEmpty(invgSearchRequestDto.getWork_no()) || StringUtils.isEmpty(invgSearchRequestDto.getVhcl_pros_no()) ) {
           logger.error("work_dates: {}, tolof_cd: {}, work_no: {}, vhcl_pros_no: {}", invgSearchRequestDto.getWork_dates(), 
           invgSearchRequestDto.getTolof_cd(), invgSearchRequestDto.getWork_no(), invgSearchRequestDto.getVhcl_pros_no());
           throw new RequiredParamException("필수파라미터가 존재하지 않습니다.");
      }
      List<InsInfo> insInfoList = insInfoRepository.findDynamicQuery(invgSearchRequestDto.getWork_dates(), 
                                         invgSearchRequestDto.getTolof_cd(), 
                                         invgSearchRequestDto.getWork_no(), 
                                         invgSearchRequestDto.getVhcl_pros_no(),
                                         invgSearchRequestDto.getVhno_chng_yn(), 
                                         invgSearchRequestDto.getNtpd_chng_yn());
      return responseService.getListResult(insInfoList); 
    }
  
}
package com.du11.coreapi.service.impl;

import com.du11.coreapi.dto.request.SRDetailRequestDTO;
import com.du11.coreapi.dto.response.SRDetailResponseDTO;
import com.du11.coreapi.entity.SRDetail;
import com.du11.coreapi.entity.SRUser;
import com.du11.coreapi.entity.SRmgIstmInfo;
import com.du11.coreapi.entity.SRmgSrIz;
import com.du11.coreapi.repository.SRDetailRepository;
import com.du11.coreapi.service.SRDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

/**
 * This is implements of 'SRDetail' service
 * @author tabac
 */
public class SRDetailServiceImpl implements SRDetailService {
    @Autowired
    private SRDetailRepository srDetailRepository;

    @Override
    public SRDetailResponseDTO getSRDetailById(SRDetailRequestDTO srDetailRequestDTO) {
        SRDetail srDetail = srDetailRepository.getSrDetailBySrId(srDetailRequestDTO);

        return SRDetailResponseDTO.builder()
                .srId(srDetail.getSrmgIstmInfo().getSrId())
                .srNm(srDetail.getSrmgIstmInfo().getSrNm())
                .biz(srDetail.getSrmgSrIz().getTskC())
                .state(srDetail.getSrmgSrIz().getPrgSts())
                .dueDate(srDetail.getSrmgSrIz().getFshExpDt())
                .chgMng(srDetail.getSrmgIstmInfo().getChPsicNm())
                .devMng(srDetail.getSrUser().getFnm())
                .allocDt(srDetail.getSrmgSrIz().getAsnDt())
                .strDt(srDetail.getSrmgSrIz().getStrtdt())
                .endDt(srDetail.getSrmgSrIz().getEnddt())
                .srMo(srDetail.getSrmgSrIz().getSrMo())
                .build();
    }

    @Override
    public SRDetailResponseDTO updateMemo(SRDetailRequestDTO srDetailRequestDTO) {
        SRDetailResponseDTO result = this.getSRDetailById(srDetailRequestDTO);
        result.setSrMo(srDetailRequestDTO.getMemo());

        srDetailRepository.updateMemo(result);

        return SRDetailResponseDTO.builder()
                .srMo(result.getSrMo())
                .build();
    }

    @Override
    public SRDetailResponseDTO updateSRDetail(SRDetailRequestDTO srDetailRequestDTO) {
        SRDetailResponseDTO result = this.getSRDetailById(srDetailRequestDTO);
        result.setSrMo(srDetailRequestDTO.getMemo());
        result.setSrNm(srDetailRequestDTO.getSrNm());
        result.setBiz(srDetailRequestDTO.getBiz());
        result.setState(srDetailRequestDTO.getState());
        result.setDueDate(srDetailRequestDTO.getDueDate());
        result.setChgMng(srDetailRequestDTO.getChgMng());
        result.setDevMng(srDetailRequestDTO.getDevMng());
        result.setAllocDt(srDetailRequestDTO.getAllocDt());
        result.setStrDt(srDetailRequestDTO.getStrDt());
        result.setEndDt(srDetailRequestDTO.getEndDt());

        srDetailRepository.updateSRDetail(result);

        return SRDetailResponseDTO.builder()
                .srNm(result.getSrNm())
                .srMo(result.getSrMo())
                .biz(result.getBiz())
                .state(result.getState())
                .dueDate(result.getDueDate())
                .chgMng(result.getChgMng())
                .devMng(result.getDevMng())
                .allocDt(result.getAllocDt())
                .strDt(result.getStrDt())
                .endDt(result.getEndDt())
                .build();
//        SRDetail result = new SRDetail();
//        SRmgSrIz srmgSrIz = new SRmgSrIz();
//        SRmgIstmInfo srmgIstmInfo = new SRmgIstmInfo();
//        SRUser srUser = new SRUser();
//
//        srmgIstmInfo.setSrNm(srDetailRequestDTO.getSrNm());
//        srmgSrIz.setSrMo(srDetailRequestDTO.getMemo());
//        srmgSrIz.setPrgSts(srDetailRequestDTO.getState());
//        srmgSrIz.setFshExpDt(srDetailRequestDTO.getDueDate());
//        srmgIstmInfo.setChPsicNm(srDetailRequestDTO.getChgMng());
//        srUser.setFnm(srDetailRequestDTO.getDevMng());
//        srmgSrIz.setAsnDt(srDetailRequestDTO.getAllocDt());
//        srmgSrIz.setStrtdt(srDetailRequestDTO.getStrDt());
//        srmgSrIz.setEnddt(srDetailRequestDTO.getEndDt());
//
//
//        SRDetail srDetail = SRDetail.builder()
//                .srmgIstmInfo(srmgIstmInfo)
//                .srmgSrIz(srmgSrIz)
//                .srUser(srUser)
//                .build();
//        srDetailRepository.updateSRDetail(srDetail);
//
//        return SRDetailResponseDTO;
    }
}

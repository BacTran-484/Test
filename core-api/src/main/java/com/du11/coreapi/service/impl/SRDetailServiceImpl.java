package com.du11.coreapi.service.impl;

import com.du11.coreapi.dto.request.SRDetailRequestDTO;
import com.du11.coreapi.dto.response.SRDetailResponseDTO;
import com.du11.coreapi.entity.SRDetail;
import com.du11.coreapi.repository.SRDetailRepository;
import com.du11.coreapi.service.SRDetailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class SRDetailServiceImpl implements SRDetailService {
    @Autowired
    private SRDetailRepository srDetailRepository;

    @Override
    public SRDetailResponseDTO getSRDetailById(SRDetailRequestDTO srDetailRequestDTO) {
        SRDetail srDetail = srDetailRepository.getSrDetailBySrId(srDetailRequestDTO);
        return SRDetailResponseDTO.builder()
                .srId(srDetail.getSrmgSrIz().getSrId())
                .memo(srDetail.getSrmgSrIz().getSrMo())
                .build();
    }

//    @Override
//    public SRDetailResponseDTO createMemo(String srMo) {
//        SRmgSrIz srIz = new SRmgSrIz();
//        srIz.setSrMo(srMo);
//
//        srDetailRepository.createMemo(srMo);
//
//        return SRDetailResponseDTO.builder()
//                .memo(srIz.getSrMo())
//                .build();
//    }

    @Override
    public SRDetailResponseDTO updateMemo(SRDetailRequestDTO srDetailRequestDTO) {

//        SRDetail srDetail = srDetailRepository.getSrDetailBySrId(srDetailRequestDTO.getSrId());
//        srDetail.setSrmgSrIz(srDetailRequestDTO.getMemo());
//
//        srDetailRepository.updateMemo(srDetail.getSrmgSrIz().getSrMo());
//
//        return SRDetailResponseDTO.builder()
//                .memo(srDetail.getSrmgSrIz().getSrMo())
//                .build();
        return null;
    }
}

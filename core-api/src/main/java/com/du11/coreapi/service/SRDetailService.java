package com.du11.coreapi.service;

import com.du11.coreapi.dto.request.SRDetailRequestDTO;
import com.du11.coreapi.dto.response.SRDetailResponseDTO;

/**
 * This is service of SRDetail
 * There are 2 method implement 2 APIs
 * @author tabac
 */
public interface SRDetailService {
    SRDetailResponseDTO getSRDetailById(SRDetailRequestDTO srDetailRequestDTO);

//    SRDetailResponseDTO createMemo(String srMo);

    SRDetailResponseDTO updateMemo(SRDetailRequestDTO srDetailRequestDTO);

//    SRDetailResponseDTO updateSRDetail(SRDetailRequestDTO srDetailRequestDTO);
}

package com.du11.coreapi.service;

import com.du11.coreapi.dto.request.SRDetailRequestDTO;
import com.du11.coreapi.dto.response.SRDetailResponseDTO;

public interface SRDetailService {
    SRDetailResponseDTO getSRDetailById(SRDetailRequestDTO srDetailRequestDTO);

//    SRDetailResponseDTO createMemo(String srMo);

    SRDetailResponseDTO updateMemo(SRDetailRequestDTO srDetailRequestDTO);
}

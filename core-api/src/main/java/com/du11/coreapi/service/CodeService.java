package com.du11.coreapi.service;

import com.du11.coreapi.dto.request.SubCodeRequestDTO;
import com.du11.coreapi.dto.response.SelectBoxResponseDTO;

import java.util.List;

public interface CodeService {
    void deleteSubCode(SubCodeRequestDTO subCodeRequestDTO);
    List<SelectBoxResponseDTO> findMainCodeByDropdown();
    List<SelectBoxResponseDTO> findSubCodeForCompanyInfo();
}

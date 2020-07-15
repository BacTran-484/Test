package com.du11.coreapi.service;

import com.du11.coreapi.dto.request.SubCodeRequestDTO;
import com.du11.coreapi.dto.response.SelectBoxResponseDTO;
import com.du11.coreapi.entity.Code;

import java.util.List;

public interface CodeService {
//    CodeResponseDTO createCode(CodeRequestDTO codeRequestDTO);
//    CodeResponseDTO updateCode(CodeRequestDTO codeRequestDTO);
    void deleteSubCode(SubCodeRequestDTO subCodeRequestDTO);
    List<SelectBoxResponseDTO> findMainCodeByDropdown();
    Code findSubCodeForCompanyInfo(SubCodeRequestDTO subCodeRequestDTO);
}

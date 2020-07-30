package com.du11.coreapi.service;

import com.du11.coreapi.dto.request.SubCodeRequestDTO;
import com.du11.coreapi.dto.response.SelectBoxResponseDTO;
import com.du11.coreapi.dto.request.CodeRequestDTO;
import com.du11.coreapi.dto.response.CodeResponseDTO;
import com.du11.coreapi.exception.EntityNotFoundException;

import java.util.List;

public interface CodeService {
    void deleteSubCode(SubCodeRequestDTO subCodeRequestDTO);
    List<SelectBoxResponseDTO> findMainCodeByDropdown();
    List<SelectBoxResponseDTO> findSubCodeForCompanyInfo();

    CodeResponseDTO createCode(CodeRequestDTO codeRequestDTO);
    CodeResponseDTO updateCode(CodeRequestDTO codeRequestDTO) throws EntityNotFoundException;
    void deleteMainCode(String cdId) throws EntityNotFoundException;

    CodeResponseDTO findMainCodeById(String id);
    List<CodeResponseDTO> findAllMainCode(int limit, int offset, String fieldName, String ascDesc);
    List<SelectBoxResponseDTO> findSubCodeForUserType();
}

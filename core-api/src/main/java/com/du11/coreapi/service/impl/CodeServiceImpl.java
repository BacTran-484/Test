package com.du11.coreapi.service.impl;

import com.du11.coreapi.dto.request.SubCodeRequestDTO;
import com.du11.coreapi.dto.response.SelectBoxResponseDTO;
import com.du11.coreapi.entity.Code;
import com.du11.coreapi.entity.CodeKey;
import com.du11.coreapi.repository.CodeRepository;
import com.du11.coreapi.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeRepository codeRepository;

//    @Override
//    public CodeResponseDTO createCode(CodeRequestDTO codeRequestDTO) {
//
//        key.setCdId(codeRequestDTO.getCode());
//        key.setDtlCdId(codeRequestDTO.getCode());
//        code.setKey(key);
//        code.setDtlCdNm(codeRequestDTO.getName());
//        code.setDlDt(codeRequestDTO.getExpDate());
//
//        codeRepository.createCode(code);
//        Code newCode = codeRepository.findCodeById(codeRequestDTO.getCode());
//        return CodeResponseDTO.builder()
//                .codeId(newCode.getKey().getCdId())
//                .detailCodeId(newCode.getKey().getDtlCdId())
//                .name(newCode.getDtlCdNm())
//                .expDate(newCode.getDlDt())
//                .build();
//    }

//    @Override
//    public CodeResponseDTO updateCode(CodeRequestDTO codeRequestDTO) {
//
//        key.setCdId(codeRequestDTO.getCode());
//        key.setDtlCdId(codeRequestDTO.getCode());
//        code.setKey(key);
//        code.setDtlCdNm(codeRequestDTO.getName());
//        code.setDlDt(codeRequestDTO.getExpDate());
//
//        codeRepository.updateCode(code);
//        Code updatedCode = codeRepository.findCodeById(codeRequestDTO.getCode());
//        return CodeResponseDTO.builder()
//                .codeId(updatedCode.getKey().getCdId())
//                .detailCodeId(updatedCode.getKey().getDtlCdId())
//                .name(updatedCode.getDtlCdNm())
//                .expDate(updatedCode.getDlDt())
//                .build();
//    }

    @Override
    public void deleteSubCode(SubCodeRequestDTO subCodeRequestDTO) {
        Code code = new Code();
        CodeKey key = new CodeKey();
        key.setCdId(subCodeRequestDTO.getCdId());
        key.setDtlCdId(subCodeRequestDTO.getDtlCdId());
        code.setKey(key);

        codeRepository.deleteSubCode(subCodeRequestDTO.getCdId(), subCodeRequestDTO.getDtlCdId());
    }

    @Override
    public List<SelectBoxResponseDTO> findMainCodeByDropdown() {
        return codeRepository.findMainCodeByDropdown().stream().map(
                code -> new SelectBoxResponseDTO(code.getKey().getCdId(), code.getDtlCdNm())
        ).collect(Collectors.toList());
    }

    @Override
    public Code findSubCodeForCompanyInfo(SubCodeRequestDTO subCodeRequestDTO) {
        return codeRepository.findSubCodeForCompanyInfo(subCodeRequestDTO.getRgrEno());
    }


}

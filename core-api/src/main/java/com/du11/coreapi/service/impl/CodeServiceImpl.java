package com.du11.coreapi.service.impl;

import com.du11.coreapi.dto.request.SubCodeRequestDTO;
import com.du11.coreapi.dto.response.SelectBoxResponseDTO;
import com.du11.coreapi.entity.Code;
import com.du11.coreapi.entity.CodeKey;
import com.du11.coreapi.dto.request.CodeRequestDTO;
import com.du11.coreapi.dto.response.CodeResponseDTO;
import com.du11.coreapi.exception.EntityNotFoundException;
import com.du11.coreapi.repository.CodeRepository;
import com.du11.coreapi.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class  CodeServiceImpl implements CodeService {
    int commonCode;
    public Code code = new Code();
    public CodeKey codeKey = new CodeKey();

    @Autowired
    private CodeRepository codeRepository;

    @Override
    public void deleteSubCode(SubCodeRequestDTO subCodeRequestDTO) {
        codeKey.setCdId(subCodeRequestDTO.getCdId());
        codeKey.setDtlCdId(subCodeRequestDTO.getDtlCdId());
        code.setKey(codeKey);

        codeRepository.deleteSubCode(subCodeRequestDTO.getCdId(), subCodeRequestDTO.getDtlCdId());
    }

    @Override
    public List<SelectBoxResponseDTO> findMainCodeByDropdown() {
        return codeRepository.findMainCodeByDropdown().stream().map(
                code -> new SelectBoxResponseDTO(code.getKey().getCdId(), code.getDtlCdNm())
        ).collect(Collectors.toList());
    }

    @Override
    public List<SelectBoxResponseDTO> findSubCodeForCompanyInfo() {
        return codeRepository.findSubCodeForCompanyInfo().stream().map(
                code -> new SelectBoxResponseDTO(code.getKey().getCdId(), code.getDtlCdNm())
        ).collect(Collectors.toList());
    }

    @Override
    public CodeResponseDTO createCode(CodeRequestDTO codeRequestDTO) {
        Code code = new Code();
        CodeKey codeKey = new CodeKey();
        codeKey.setCdId(codeRequestDTO.getCommonCode());
        codeKey.setDtlCdId(codeRequestDTO.getSubCode());
        code.setKey(codeKey);
        code.setDtlCdNm(codeRequestDTO.getName());
        code.setDlDt(codeRequestDTO.getExpDate());
        CodeResponseDTO codeResponseDTO = new CodeResponseDTO();
        codeRepository.createCode(code);

        Code res = codeRepository.findMainCodeById(codeRequestDTO.getCommonCode());
        codeResponseDTO.setCdId(res.getKey().getCdId());
        codeResponseDTO.setDtlCdId(res.getKey().getDtlCdId());
        codeResponseDTO.setName(res.getDtlCdNm());
        codeResponseDTO.setExpDate(res.getDlDt());

        return CodeResponseDTO.builder()
                .cdId(code.getKey().getCdId())
                .dtlCdId(code.getKey().getDtlCdId())
                .name(code.getDtlCdNm())
                .expDate(code.getRgDt())
                .build();
    }

    @Override
    public List<CodeResponseDTO> findAllMainCode(int limit, int offset, String fieldName, String ascDesc) {
        List<CodeResponseDTO> codeRespons = new ArrayList<>();
        List<Code> codes = codeRepository.findAllMainCode(limit, offset, fieldName, ascDesc);
        for (Code code : codes) {
            codeRespons.add(CodeResponseDTO.builder()
                    .dtlCdId(code.getDtlCdNm())
                    .build());
        }
        return codeRespons;
    }

    @Override
    public List<SelectBoxResponseDTO> findSubCodeForUserType() {
        return codeRepository.findSubCodeForUserType().stream().map(
                code -> new SelectBoxResponseDTO(code.getKey().getCdId(), code.getDtlCdNm())
        ).collect(Collectors.toList());
    }

    @Override
    public CodeResponseDTO findMainCodeById(String id) {
        Code codes = codeRepository.findMainCodeById(id);
        return CodeResponseDTO.builder()
                .cdId(codes.getKey().getCdId())
                .dtlCdId(codes.getDtlCdNm()).build();

    }


    public CodeResponseDTO updateCode(CodeRequestDTO codeRequestDTO) throws EntityNotFoundException {

        codeKey.setCdId(codeRequestDTO.getCommonCode());
        codeKey.setDtlCdId(codeRequestDTO.getSubCode());
        code.setKey(codeKey);
        code.setDtlCdNm(codeRequestDTO.getName());
        code.setDlDt(codeRequestDTO.getExpDate());
        CodeResponseDTO codeResponseDTO = new CodeResponseDTO();

        Code existEntity = codeRepository.findMainCodeById(codeRequestDTO.getCommonCode());
        if (existEntity == null){
            throw new EntityNotFoundException();
        }

        codeRepository.updateCode(code);
        Code res = codeRepository.findMainCodeById(codeRequestDTO.getCommonCode());
        codeResponseDTO.setCdId(res.getKey().getCdId());
        codeResponseDTO.setDtlCdId(res.getKey().getDtlCdId());
        codeResponseDTO.setName(res.getDtlCdNm());
        codeResponseDTO.setExpDate(res.getDlDt());
        return CodeResponseDTO.builder()
                .cdId(code.getKey().getCdId())
                .dtlCdId(code.getKey().getDtlCdId())
                .name(code.getDtlCdNm())
                .expDate(code.getRgDt())
                .build();
    }

    @Override
    public void deleteMainCode(String cdId){
        commonCode = codeRepository.deleteMainCode(cdId);
    }
}

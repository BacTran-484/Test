package com.du11.coreapi.controller;

import com.du11.coreapi.dto.request.SelectBoxRequestDTO;
import com.du11.coreapi.dto.request.SubCodeRequestDTO;
import com.du11.coreapi.dto.response.SelectBoxResponseDTO;
import com.du11.coreapi.dto.response.SubCodeResponseDTO;
import com.du11.coreapi.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/codes")
public class CodeController {

    private CodeService codeService;

    @Autowired
    public void CodeService(CodeService codeService) {
        this.codeService = codeService;
    }

    @DeleteMapping("/subcode/delete/{dtlCdId}")
    public ResponseEntity<SubCodeResponseDTO> deleteSubCode(@PathVariable("dtlCdId") SubCodeRequestDTO dtlCdId) {
        codeService.deleteSubCode(dtlCdId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/subcode/get/{rgrEno}")
//    public ResponseEntity<SelectBoxResponseDTO> findSubCodeForCompanyInfo(@PathVariable SelectBoxRequestDTO rgrEno) {
//        SelectBoxResponseDTO selectBoxResponseDTO = codeService.findSubCodeForCompanyInfo(rgrEno);
//        return new ResponseEntity<>(selectBoxResponseDTO, HttpStatus.OK);
//    }

    @GetMapping("/maincode/selectbox")
    public ResponseEntity<List<SelectBoxResponseDTO>> findMainCodeByDropdown() {
        return new ResponseEntity<>(codeService.findMainCodeByDropdown(), HttpStatus.OK);
    }

}


package com.du11.coreapi.controller;

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

    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }


    /**
     * @author: tabac
     * this method delete subcode
     * @param dtlCdId
     */
    @DeleteMapping("/subcode/delete/{dtlCdId}")
    public ResponseEntity<SubCodeResponseDTO> deleteSubCode(@RequestBody SubCodeRequestDTO dtlCdId) {
        codeService.deleteSubCode(dtlCdId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * @author: tabac
     */
    @GetMapping("/subcode/get/company")
    public ResponseEntity<List<SelectBoxResponseDTO>> findSubCodeForCompanyInfo() {
        return new ResponseEntity<>(codeService.findSubCodeForCompanyInfo(), HttpStatus.OK);
    }

    /**
     * author: tabac
     * this method get all maincode for dropdown
     */
    @GetMapping("/maincode/dropdown")
    public ResponseEntity<List<SelectBoxResponseDTO>> findMainCodeByDropdown() {
        return new ResponseEntity<>(codeService.findMainCodeByDropdown(), HttpStatus.OK);
    }

}


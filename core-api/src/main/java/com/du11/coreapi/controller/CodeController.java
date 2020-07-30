package com.du11.coreapi.controller;

import com.du11.coreapi.dto.request.SubCodeRequestDTO;
import com.du11.coreapi.dto.response.SelectBoxResponseDTO;
import com.du11.coreapi.dto.response.SubCodeResponseDTO;
import com.du11.coreapi.dto.request.CodeRequestDTO;
import com.du11.coreapi.dto.response.CodeResponseDTO;
import com.du11.coreapi.dto.response.PaginationResponse;
import com.du11.coreapi.exception.EntityNotFoundException;
import com.du11.coreapi.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/codes")
public class CodeController {
    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping
    public ResponseEntity<CodeResponseDTO> createCode (@Valid @RequestBody CodeRequestDTO codeRequestDTO){
        CodeResponseDTO codeResponseDTO = codeService.createCode(codeRequestDTO);
        return new ResponseEntity<>(codeResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CodeResponseDTO> updateCode (@Valid @RequestBody CodeRequestDTO codeRequestDTO)
            throws EntityNotFoundException {
        CodeResponseDTO codeResponseDTO = codeService.updateCode(codeRequestDTO);
        return new ResponseEntity<>(codeResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/subcode")
    public ResponseEntity<SubCodeResponseDTO> deleteSubCode(@RequestBody SubCodeRequestDTO dtlCdId) {
        codeService.deleteSubCode(dtlCdId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/subcode/company")
    public ResponseEntity<List<SelectBoxResponseDTO>> findSubCodeForCompanyInfo() {
        return new ResponseEntity<>(codeService.findSubCodeForCompanyInfo(), HttpStatus.OK);
    }

    @GetMapping("/subcode/usertype")
    public ResponseEntity<List<SelectBoxResponseDTO>> findSubCodeForUserType() {
        return new ResponseEntity<>(codeService.findSubCodeForUserType(), HttpStatus.OK);
    }

    @GetMapping("/maincode/dropdown")
    public ResponseEntity<List<SelectBoxResponseDTO>> findMainCodeByDropdown() {
        return new ResponseEntity<>(codeService.findMainCodeByDropdown(), HttpStatus.OK);
    }

    @DeleteMapping("/main/{cdId}")
    public void deleteMainCode (@PathVariable("cdId") String cdId) throws EntityNotFoundException {
        codeService.deleteMainCode(cdId);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<CodeResponseDTO>> findAllMainCode(@RequestParam("limit") int limit, @RequestParam("offset") int offset, @RequestParam("field") String field, @RequestParam("ascDesc") String ascDesc ) {
        PaginationResponse<CodeResponseDTO> response = new PaginationResponse<>();
        response.setLimit(limit);
        response.setOffset(offset + limit);
        response.setFieldName(field);
        response.setAscDesc(ascDesc);
        response.setData(codeService.findAllMainCode(limit,offset,field,ascDesc));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CodeResponseDTO> findMainCodeById(@PathVariable("id") String id) {
        CodeResponseDTO codeResponseDTO = codeService.findMainCodeById(id);
        return new ResponseEntity<>(codeResponseDTO, HttpStatus.OK);
    }

}

package com.du11.coreapi.controller;

import com.du11.coreapi.dto.request.SRDetailRequestDTO;
import com.du11.coreapi.dto.response.SRDetailResponseDTO;
import com.du11.coreapi.service.SRDetailService;
import com.du11.coreapi.service.SRUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("srDetail")
public class SRDetailController {
    @Autowired
    private SRDetailService srDetailService;

    public SRDetailController(SRDetailService srDetailService) {
        this.srDetailService = srDetailService;
    }

    @PutMapping("/memo")
    public ResponseEntity<SRDetailResponseDTO> updateMemo(@Valid @RequestParam(name = "srMo") SRDetailRequestDTO srMo) {
        SRDetailResponseDTO srDetailResponseDTO = srDetailService.updateMemo(srMo);
        return new ResponseEntity<>(srDetailResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<SRDetailResponseDTO> getSRDetailById( @RequestParam(name = "srId") String srId) {
        SRDetailRequestDTO requestDTO = new SRDetailRequestDTO();
        requestDTO.setSrId(srId);
        SRDetailResponseDTO srDetailResponseDTO = srDetailService.getSRDetailById(requestDTO);
        return new ResponseEntity<>(srDetailResponseDTO, HttpStatus.OK);
    }

//    @PostMapping("/memo")
//    public ResponseEntity<SRDetailResponseDTO> createMemo(@Valid @RequestParam(name = "srMo") String srMo) {
//        SRDetailResponseDTO srDetailResponseDTO = srUserService.createMemo(srMo);
//        return new ResponseEntity<>(srDetailResponseDTO, HttpStatus.CREATED);
//    }
}

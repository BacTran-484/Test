package com.du11.coreapi.controller;

import com.du11.coreapi.dto.request.SRDetailRequestDTO;
import com.du11.coreapi.dto.response.SRDetailResponseDTO;
import com.du11.coreapi.service.SRDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("srDetail")

/**
 * This is a controller of SRDetail
 * @author: tabac
 */
public class SRDetailController {
    @Autowired
    private SRDetailService srDetailService;

    public SRDetailController(SRDetailService srDetailService) {
        this.srDetailService = srDetailService;
    }

    @GetMapping
    public ResponseEntity<SRDetailResponseDTO> getSRDetailById(@Valid @RequestParam(name = "srId") String srId) {
        SRDetailRequestDTO requestDTO = new SRDetailRequestDTO();
        requestDTO.setSrId(srId);
        SRDetailResponseDTO srDetailResponseDTO = srDetailService.getSRDetailById(requestDTO);
        return new ResponseEntity<>(srDetailResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/memo")
    public ResponseEntity<SRDetailResponseDTO> updateMemo(@Valid @RequestBody SRDetailRequestDTO srDetailRequestDTO) {
        SRDetailResponseDTO srDetailResponseDTO = srDetailService.updateMemo(srDetailRequestDTO);
        return new ResponseEntity<>(srDetailResponseDTO, HttpStatus.OK);
    }

//    @PutMapping
//    public ResponseEntity<SRDetailResponseDTO> updateSRDetail(@Valid @RequestBody SRDetailRequestDTO srDetailRequestDTO) {
//        SRDetailResponseDTO srDetailResponseDTO = srDetailService.updateSRDetail(srDetailRequestDTO);
//        return new ResponseEntity<>(srDetailResponseDTO, HttpStatus.OK);
//    }

}

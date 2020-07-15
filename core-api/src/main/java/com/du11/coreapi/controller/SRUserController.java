package com.du11.coreapi.controller;

import com.du11.coreapi.dto.request.SRUserRequestDTO;
import com.du11.coreapi.dto.response.PaginationResponseDTO;
import com.du11.coreapi.dto.response.SRUserResponseDTO;
import com.du11.coreapi.exception.EntityNotFoundException;
import com.du11.coreapi.service.SRUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class SRUserController {

    private SRUserService srUserService;
    PaginationResponseDTO<SRUserResponseDTO> responseDTO = new PaginationResponseDTO<>();

    @Autowired
    public void SRUserController(SRUserService srUserService) {
        this.srUserService = srUserService;
    }

    @PutMapping("/update")
    public ResponseEntity<SRUserResponseDTO> updateUser(@RequestBody @Valid SRUserRequestDTO srUserRequestDTO) {
        SRUserResponseDTO srUserResponseDTO = srUserService.updateUser(srUserRequestDTO);
        return new ResponseEntity<>(srUserResponseDTO, HttpStatus.OK);
    }

//    @GetMapping("/get/{empno}")
//    public ResponseEntity<PaginationResponseDTO<SRUserResponseDTO>> searchUserByEmpNo(@PathVariable("empno")  SRUserRequestDTO empno,
//                                                                     @RequestParam("limit") int limit, @RequestParam("offset") int offset)  {
//        responseDTO.setLimit(limit);
//        responseDTO.setOffset(offset);
//        responseDTO.setData(srUserService.findUserByEmpNo(empno, limit, offset));
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
////        List<SRUserResponseDTO> srUserResponseDTO = srUserService.findUserByEmpNo(empno);
////        return new ResponseEntity<>(srUserResponseDTO, HttpStatus.OK);
//    }
//
//    @GetMapping("/get/{fnm}")
//    public ResponseEntity<PaginationResponseDTO<SRUserResponseDTO>> searchUserByEmpName(@PathVariable("fnm") SRUserRequestDTO fnm,
//                                                                                        @RequestParam("limit") int limit, @RequestParam("offset") int offset)  {
//        responseDTO.setLimit(limit);
//        responseDTO.setOffset(offset);
//        responseDTO.setData(srUserService.findUserByEmpName(fnm, limit, offset));
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }
//
//    @GetMapping("/get/{grp}")
//    public ResponseEntity<PaginationResponseDTO<SRUserResponseDTO>> searchUserByEmpCompany(@PathVariable("grp") SRUserRequestDTO grp,
//                                                                          @RequestParam("limit") int limit, @RequestParam("offset") int offset)  {
//        responseDTO.setLimit(limit);
//        responseDTO.setOffset(offset);
//        responseDTO.setData(srUserService.findUserByEmpCompany(grp, limit, offset));
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }

}

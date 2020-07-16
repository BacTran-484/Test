package com.du11.coreapi.controller;

import com.du11.coreapi.dto.request.SRUserRequestDTO;
import com.du11.coreapi.dto.response.PaginationResponseDTO;
import com.du11.coreapi.dto.response.SRUserResponseDTO;
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

    private final SRUserService srUserService;
    PaginationResponseDTO<SRUserResponseDTO> responseDTO = new PaginationResponseDTO<>();

    @Autowired
    public SRUserController(SRUserService srUserService) {
        this.srUserService = srUserService;
    }


    /**
     * author: tabac
     * this method update user information
     * @param srUserRequestDTO
     */
    @PutMapping("/update")
    public ResponseEntity<SRUserResponseDTO> updateUser(@RequestBody @Valid SRUserRequestDTO srUserRequestDTO) {
        SRUserResponseDTO srUserResponseDTO = srUserService.updateUser(srUserRequestDTO);
        return new ResponseEntity<>(srUserResponseDTO, HttpStatus.OK);
    }

    /**
     * @author tabac
     * this method get user by ID
     * @param empno
     * @param
     * @param
     * @return
     */

    @GetMapping("/get/{ }")
    public ResponseEntity<List<SRUserResponseDTO>> searchUserByEmpNo(@RequestParam( name = "{empno}", required = false)  String empno, @RequestParam( name = "{fnm}", required = false)  String fnm, @RequestParam( name = "{grp}", required = false) String grp )  {
        List<SRUserResponseDTO> srUserResponseDTO = srUserService.findUserByEmpNo(empno,fnm,grp);
        return new ResponseEntity<>(srUserResponseDTO,HttpStatus.OK);
    }



}

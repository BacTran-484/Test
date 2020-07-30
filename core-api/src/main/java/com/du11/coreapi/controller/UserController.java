package com.du11.coreapi.controller;

import com.du11.coreapi.dto.request.SRDetailRequestDTO;
import com.du11.coreapi.dto.request.UserRequestDTO;
import com.du11.coreapi.dto.response.PaginationResponse;
import com.du11.coreapi.dto.response.SRDetailResponseDTO;
import com.du11.coreapi.dto.response.UserResponseDTO;
import com.du11.coreapi.entity.SRDetail;
import com.du11.coreapi.exception.EntityNotFoundException;
import com.du11.coreapi.exception.InsertExistedEntityException;
import com.du11.coreapi.service.SRUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private SRUserService srUserService;

    public UserController(SRUserService srUserService) {
        this.srUserService = srUserService;
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<UserResponseDTO>> getAllUsers(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        PaginationResponse<UserResponseDTO> response = new PaginationResponse<>();
        response.setLimit(limit);
        response.setOffset(offset + limit);
        response.setData(srUserService.getAllUsers(limit, offset));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO)
            throws InsertExistedEntityException {
        UserResponseDTO userResponseDTO = srUserService.createUser(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{empno}")
    public void deleteSRUser(@PathVariable("empno") String empno) {
        srUserService.deleteSRUserByEmpno(empno);
    }

    @GetMapping("/{empno}")
    public ResponseEntity<UserResponseDTO> findByEmpno(@PathVariable("empno") String empno) {

        return new ResponseEntity<>(srUserService.findByEmpno(empno), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserRequestDTO srUserRequestDTO) {
        UserResponseDTO srUserResponseDTO = srUserService.updateUser(srUserRequestDTO);
        return new ResponseEntity<>(srUserResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponseDTO>> searchUserBy(@RequestParam(name = "{empno}", required = false) String empno,
                                                                @RequestParam(name = "{fnm}", required = false) String fnm,
                                                                @RequestParam(name = "{grp}", required = false) String grp) throws EntityNotFoundException {
        List<UserResponseDTO> srUserResponseDTO = srUserService.findUserBy(empno, fnm, grp);
        return new ResponseEntity<>(srUserResponseDTO,HttpStatus.OK);
    }


}

package com.du11.coreapi.service;

import com.du11.coreapi.dto.request.SRDetailRequestDTO;
import com.du11.coreapi.dto.request.UserRequestDTO;
import com.du11.coreapi.dto.response.SRDetailResponseDTO;
import com.du11.coreapi.dto.response.UserResponseDTO;
import com.du11.coreapi.entity.SRDetail;
import com.du11.coreapi.exception.InsertExistedEntityException;

import java.util.List;

public interface SRUserService {
    UserResponseDTO findByEmpno(String empno);

    UserResponseDTO updateUser(UserRequestDTO srUserRequestDTO);

    List<UserResponseDTO> findUserBy(String empno, String fnm, String grp);

    List<UserResponseDTO> getAllUsers(int limit, int offset);

    void deleteSRUserByEmpno(String empno);

    /**
     * Create new entity and insert to DB
     * @param
     * **/
    UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws InsertExistedEntityException;


}

package com.du11.coreapi.service;

import com.du11.coreapi.dto.request.SRUserRequestDTO;
import com.du11.coreapi.dto.response.SRUserResponseDTO;

import java.util.List;

public interface SRUserService {

    SRUserResponseDTO updateUser(SRUserRequestDTO srUserRequestDTO);

    List<SRUserResponseDTO> findUserByEmpNo(SRUserRequestDTO srUserRequestDTO, int limit, int offset);
    List<SRUserResponseDTO> findUserByEmpName(SRUserRequestDTO srUserRequestDTO, int limit, int offset);
    List<SRUserResponseDTO> findUserByEmpCompany(SRUserRequestDTO srUserRequestDTO, int limit, int offset);
}

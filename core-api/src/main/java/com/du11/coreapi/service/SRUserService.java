package com.du11.coreapi.service;

import com.du11.coreapi.dto.request.SRUserRequestDTO;
import com.du11.coreapi.dto.response.SRUserResponseDTO;

import java.util.List;

public interface SRUserService {

    SRUserResponseDTO updateUser(SRUserRequestDTO srUserRequestDTO);

    List<SRUserResponseDTO> findUserByEmpNo(String empno, String fnm, String grp);

}

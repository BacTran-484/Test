package com.du11.coreapi.service.impl;

import com.du11.coreapi.dto.error.ApiError;
import com.du11.coreapi.dto.request.SRUserRequestDTO;
import com.du11.coreapi.dto.response.SRUserResponseDTO;
import com.du11.coreapi.entity.SRUser;
import com.du11.coreapi.exception.EntityNotFoundException;
import com.du11.coreapi.repository.SRUserRepository;
import com.du11.coreapi.service.SRUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SRUserServiceImpl implements SRUserService {

    public final List<SRUserResponseDTO> srUserResponseDTOS = new ArrayList<>();

    @Autowired
    private SRUserRepository srUserRepository;

    @Override
    public SRUserResponseDTO updateUser(SRUserRequestDTO srUserRequestDTO) {
        SRUser res = srUserRepository.findByEmpno(srUserRequestDTO.getEmpno());
        if (res == null) {
            throw new EntityNotFoundException(ApiError.builder().status(HttpStatus.NOT_FOUND.value()).build());
        } else {
            SRUser srUser = new SRUser();
            srUser.setEmpno(srUserRequestDTO.getEmpno());
            srUser.setDlYn(srUserRequestDTO.getDlyn());
            srUser.setMngrRghYn(srUserRequestDTO.getMngrRghYn());
            srUser.setGrp(srUserRequestDTO.getGrp());
            srUser.setFnm(srUserRequestDTO.getFnm());
            srUserRepository.updateUser(srUser);
            return SRUserResponseDTO.builder()
                    .empno(srUser.getEmpno())
                    .fnm(srUser.getFnm())
                    .grp(srUser.getGrp())
                    .mngrRghYn(srUser.getMngrRghYn())
                    .dlyn(srUser.getDlYn())
                    .build();
        }
    }

    @Override
    public List<SRUserResponseDTO> findUserByEmpNo(String empno, String fnm, String grp) {
        List<SRUser> srUsers = srUserRepository.findUserByEmpNo(empno,fnm,grp);
        if (srUsers == null) {
            throw new EntityNotFoundException(ApiError.builder().status(HttpStatus.NOT_FOUND.value()).build());
        }
       for (SRUser srUser : srUsers) {
           srUserResponseDTOS.add(SRUserResponseDTO.builder()
                            .empno(srUser.getEmpno())
                            .build());
       }
       return srUserResponseDTOS;
    }

}

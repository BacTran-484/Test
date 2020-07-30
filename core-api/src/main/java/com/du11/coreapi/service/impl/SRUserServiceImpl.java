package com.du11.coreapi.service.impl;

import com.du11.coreapi.dto.error.ApiError;
import com.du11.coreapi.dto.request.SRDetailRequestDTO;
import com.du11.coreapi.dto.response.SRDetailResponseDTO;
import com.du11.coreapi.entity.SRDetail;
import com.du11.coreapi.entity.SRUser;
import com.du11.coreapi.entity.SRmgSrIz;
import com.du11.coreapi.exception.EntityNotFoundException;
import com.du11.coreapi.repository.SRDetailRepository;
import com.du11.coreapi.repository.SRUserRepository;

import com.du11.coreapi.dto.error.ApiError;
import com.du11.coreapi.dto.error.ApiErrorDetail;
import com.du11.coreapi.dto.request.UserRequestDTO;
import com.du11.coreapi.dto.response.UserResponseDTO;
import com.du11.coreapi.exception.InsertExistedEntityException;
import com.du11.coreapi.service.SRUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class SRUserServiceImpl implements SRUserService {

    @Autowired
    private SRUserRepository srUserRepository;


    @Override
    public UserResponseDTO updateUser(UserRequestDTO srUserRequestDTO) {
        SRUser res = srUserRepository.findByEmpno(srUserRequestDTO.getEmpno());
//        if (res == null) {
//            throw new EntityNotFoundException(ApiError.builder().status(HttpStatus.NOT_FOUND.value()).build());
//        } else {
            SRUser srUser = new SRUser();
            srUser.setEmpno(srUserRequestDTO.getEmpno());
            srUser.setDlYn(srUserRequestDTO.getDlYn());
            srUser.setMngrRghYn(srUserRequestDTO.getMngrRghYn());
            srUser.setGrp(srUserRequestDTO.getGrp());
            srUser.setFnm(srUserRequestDTO.getFnm());
            srUserRepository.updateUser(srUser);
            return UserResponseDTO.builder()
                    .empno(srUser.getEmpno())
                    .fnm(srUser.getFnm())
                    .grp(srUser.getGrp())
                    .mngrRghYn(srUser.getMngrRghYn())
                    .dlYn(srUser.getDlYn())
                    .build();

    }

    @Override
    public List<UserResponseDTO> findUserBy(String empno, String fnm, String grp) {
        List<UserResponseDTO> srUserResponseDTOS = new ArrayList<>();
        List<SRUser> srUsers = srUserRepository.findUserBy(empno, fnm, grp);
        if (srUsers == null) {
//            throw new EntityNotFoundException(ApiError.builder().status(HttpStatus.NOT_FOUND.value()).build());
        } else {
            for (SRUser srUser : srUsers) {
                srUserResponseDTOS.add(UserResponseDTO.builder()
                        .empno(srUser.getEmpno())
                        .fnm(srUser.getFnm())
                        .grp(srUser.getGrp())
                        .mngrRghYn(srUser.getMngrRghYn())
                        .dlYn(srUser.getDlYn())
                        .build());
            }
        }
       return srUserResponseDTOS;
    }

    @Override
    public List<UserResponseDTO> getAllUsers(int limit, int offset) {
        List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
        List<SRUser> users = srUserRepository.findAll(limit, offset);
        if (users == null) {
//            throw new EntityNotFoundException(ApiError.builder().status(HttpStatus.NOT_FOUND.value()).build());
        } else {
            for (SRUser srUser : users) {
                userResponseDTOS.add(UserResponseDTO.builder()
                        .empno(srUser.getEmpno())
                        .fnm(srUser.getFnm())
                        .grp(srUser.getGrp())
                        .mngrRghYn(srUser.getMngrRghYn())
                        .dlYn(srUser.getDlYn())
                        .build());
            }
        }
        return userResponseDTOS;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws InsertExistedEntityException {

        SRUser user = new SRUser();
        user.setEmpno(userRequestDTO.getEmpno());
        user.setFnm(userRequestDTO.getFnm());
        user.setGrp(userRequestDTO.getGrp());
        user.setMngrRghYn(userRequestDTO.getMngrRghYn());
        user.setDlYn(userRequestDTO.getDlYn());

        try {
            srUserRepository.createUser(user);
        } catch (DuplicateKeyException e) {
            log.error(e.getMessage(), e);
            ApiErrorDetail errorDetail = ApiErrorDetail.builder()
                    .fieldName("empno").message("Empno is existed.")
                    .build();
            ApiError apiError = ApiError.builder().errors(Collections.singletonList(errorDetail)).build();
            throw new InsertExistedEntityException(apiError);
        }

        return UserResponseDTO.builder()
                .empno(user.getEmpno())
                .fnm(user.getFnm())
                .grp(user.getGrp())
                .mngrRghYn(user.getMngrRghYn())
                .dlYn(user.getDlYn())
                .build();
    }


    @Override
    public UserResponseDTO findByEmpno(String empno) {
        SRUser existEntity = srUserRepository.findByEmpno(empno);
        return UserResponseDTO.builder().empno(existEntity.getEmpno()).grp(existEntity.getGrp())
                .mngrRghYn(existEntity.getMngrRghYn())
                .dlYn(existEntity.getDlYn()).fnm(existEntity.getFnm()).build();
    }

    @Override
    public void deleteSRUserByEmpno(String empno) {
        srUserRepository.deleteSRUserById(empno);
    }




}

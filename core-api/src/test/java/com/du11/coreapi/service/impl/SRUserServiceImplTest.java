package com.du11.coreapi.service.impl;

import com.du11.coreapi.dto.request.UserRequestDTO;
import com.du11.coreapi.dto.response.UserResponseDTO;
import com.du11.coreapi.entity.SRUser;
import com.du11.coreapi.exception.InsertExistedEntityException;
import com.du11.coreapi.repository.SRUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class SRUserServiceImplTest {

    @InjectMocks
    private SRUserServiceImpl srUserService;

    @Mock
    private SRUserRepository srUserRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUser_InputValid_ShouldReturnProperlyResponse() throws Exception {
        //Mock input
        UserRequestDTO mockDTO = UserRequestDTO.builder()
                .empno("bac")
                .fnm("Bac")
                .build();
        //Mock dependency
        when(srUserRepository.createUser(any(SRUser.class))).thenReturn(1);

        //Run function can test voi mock input o tren
        UserResponseDTO result = this.srUserService.createUser(mockDTO);

        //Kiem tra ket qua
        assertEquals("bac", result.getEmpno());
        assertEquals("Bac", result.getFnm());

    }

    @Test
    void createUser_InputExistedOne_ShouldThrowInsertExistedEntityException() {
        //Mock input
        UserRequestDTO mockDTO = UserRequestDTO.builder()
                .empno("bac")
                .fnm("Bac")
                .build();
        //Mock dependency
        when(srUserRepository.createUser(any(SRUser.class))).thenThrow(
                new DuplicateKeyException("Duplicate entry 'bac' for key 'PRIMARY'"));

        //Run function can test voi mock input o tren
        InsertExistedEntityException exception = assertThrows(InsertExistedEntityException.class,
                () -> {
                    this.srUserService.createUser(mockDTO);
                });

        assertEquals("Duplicate entry 'bac' for key 'PRIMARY'", exception.getMessage());

    }

    @Test
    void getAllUsers (){
        List<SRUser> mockList = new ArrayList<>();
        mockList.add(new SRUser("123","duy","1","Y","N"));
        when (srUserRepository.findAll(anyInt(),anyInt())).thenReturn(mockList);
        List<UserResponseDTO> result = this.srUserService.getAllUsers(1,0);

        assertNotNull(result);
        assertEquals(1,result.size());
        assertEquals("123",result.get(0).getEmpno());
    }

    @Test
    void updateUser_InputValid_ShouldReturnProperlyResponse() {
        SRUser srUser = new SRUser();

        //mock input
        UserRequestDTO mockDTO = UserRequestDTO.builder()
                .empno("string")
                .fnm("string2")
                .grp("3")
                .mngrRghYn("Y")
                .dlYn("N")
                .build();

        //mock dependency
        when(srUserRepository.findByEmpno(any(String.class))).thenReturn(srUser);
        when(srUserRepository.updateUser(any(SRUser.class))).thenReturn(1);

        //run function voi mock input o tren
        UserResponseDTO result = this.srUserService.updateUser(mockDTO);

        assertEquals("string", result.getEmpno());
        assertEquals("string2", result.getFnm());
        assertEquals("3", result.getGrp());
        assertEquals("Y", result.getMngrRghYn());
        assertEquals("N", result.getDlYn());

    }

    @Test
    void findUserBy_InputValid_ShouldReturnProperlyResponse() {
        List<SRUser> mockList = new ArrayList<>();

    }
}
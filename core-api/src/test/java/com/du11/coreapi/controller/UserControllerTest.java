package com.du11.coreapi.controller;

import com.du11.coreapi.dto.error.ApiError;
import com.du11.coreapi.dto.error.ApiErrorDetail;
import com.du11.coreapi.exception.InsertExistedEntityException;
import com.du11.coreapi.service.SRUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.du11.coreapi.dto.request.UserRequestDTO;
import com.du11.coreapi.dto.response.UserResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matcher.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")
@Slf4j
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SRUserService srUserService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<UserResponseDTO> userList;

    @BeforeEach
    void setUp() {
        this.userList = new ArrayList<>();
        this.userList.add(UserResponseDTO.builder().empno("1").fnm("Bac").build());
        this.userList.add(UserResponseDTO.builder().empno("2").fnm("Duy").build());
        this.userList.add(UserResponseDTO.builder().empno("3").fnm("Bach").build());
    }

    @WithMockUser(value = "D00001")
    @Test
    void testCreateUser_InputValid_ShouldCreateUser() throws Exception {

        //Mock dependency
        given(srUserService.createUser(any(UserRequestDTO.class))).willReturn(
                UserResponseDTO.builder().empno("4").fnm("Thao").build()
        );

        //Mock input
        UserRequestDTO mockUserRequestDTO = UserRequestDTO.builder().empno("3").fnm("ThaoFake").build();

        //Perform API and assert output
        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUserRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.empno", is("4")))
                .andExpect(jsonPath("$.fnm", is("Thao")))
        ;
    }

    @WithMockUser(value = "D00001")
    @Test
    void testCreateUser_InputExisted_ShouldReturnBadRequest() throws Exception {
        //Mock dependency
        ApiErrorDetail errorDetail = ApiErrorDetail.builder()
                .fieldName("empno").message("Empno is existed.")
                .build();
        ApiError apiError = ApiError.builder().errors(Collections.singletonList(errorDetail)).build();
        given(srUserService.createUser(any(UserRequestDTO.class))).willThrow(
                new InsertExistedEntityException(apiError)
        );

        //Mock input
        UserRequestDTO mockUserRequestDTO = UserRequestDTO.builder().empno("3").fnm("ThaoFake").build();

        //Perform API and assert output
        MvcResult result = this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUserRequestDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(HttpStatus.BAD_REQUEST.value())))
                .andExpect(jsonPath("errors[*].fieldName", Matchers.containsInAnyOrder("empno")))
                //.andExpect(jsonPath("errors[0].fieldName", is("empno")))
                //.andExpect(jsonPath("errors[*].message", Matchers.containsInAnyOrder(("Empno is existed."))))
                .andExpect(jsonPath("errors[0].message", is(("Empno is existed."))))
                .andReturn()
        ;

        log.info("Response: {}", result.getResponse().getContentAsString());
    }

    @WithMockUser(value = "D00001")
    @Test
    void testUpdateUser_InputValid_ShouldUpdateUser() throws Exception {
        //mock dependency
        given(srUserService.updateUser(any(UserRequestDTO.class))).willReturn(
                UserResponseDTO.builder().empno("2").fnm("Bac").build()
        );

        //mock input
        UserRequestDTO mockUserRequestDTO = UserRequestDTO.builder()
                .empno("2")
                .fnm("Bac")
                .build();

        //perform API and assert output
        this.mockMvc.perform(put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockUserRequestDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.empno", is("2")))
            .andExpect(jsonPath("$.fnm", is("Bac")));
    }
}

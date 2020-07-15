package com.du11.coreapi.controller;

import com.du11.coreapi.dto.request.CreateMainCodeRequest;
import com.du11.coreapi.dto.response.MainCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/codes")
public class CreateMainCode {

    private  com.du11.coreapi.service.CreateMainCode createMainCodeService;


    @Autowired
    public CreateMainCode(com.du11.coreapi.service.CreateMainCode createMainCodeService) {
        this.createMainCodeService = createMainCodeService;
    }

    @PostMapping("/main")
    public ResponseEntity<MainCodeResponse> createMainCode(@RequestBody @Valid CreateMainCodeRequest createMainCodeRequest){
        MainCodeResponse mainCodeResponse = createMainCodeService.createMainCode(createMainCodeRequest);
        return new ResponseEntity<>(mainCodeResponse, HttpStatus.CREATED);
    }

}

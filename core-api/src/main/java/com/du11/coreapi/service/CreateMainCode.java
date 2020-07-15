package com.du11.coreapi.service;

import com.du11.coreapi.dto.request.CreateMainCodeRequest;
import com.du11.coreapi.dto.response.MainCodeResponse;

public interface CreateMainCode {
    MainCodeResponse createMainCode(CreateMainCodeRequest createMainCodeRequest);
}

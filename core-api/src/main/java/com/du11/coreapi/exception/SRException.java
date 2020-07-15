package com.du11.coreapi.exception;

import com.du11.coreapi.dto.error.ApiError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SRException extends RuntimeException {

    private final ApiError apiError;
}

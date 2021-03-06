package com.du11.coreapi.exception;

import com.du11.coreapi.dto.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizationException extends SRException {

    public UnAuthorizationException(ApiError apiError) {
        super(apiError);
    }
}

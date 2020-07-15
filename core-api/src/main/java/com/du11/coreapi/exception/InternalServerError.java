package com.du11.coreapi.exception;

import com.du11.coreapi.dto.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerError extends SRException {

    public InternalServerError(ApiError apiError) {
        super(apiError);
    }
}

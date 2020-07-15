package com.du11.coreapi.exception;

import com.du11.coreapi.dto.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends SRException {

    public EntityNotFoundException(ApiError apiError) {
        super(apiError);
    }
}
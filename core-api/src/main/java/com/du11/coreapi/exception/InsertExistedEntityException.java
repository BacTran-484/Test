package com.du11.coreapi.exception;

import com.du11.coreapi.dto.error.ApiError;

public class InsertExistedEntityException extends SRException {

    public InsertExistedEntityException(ApiError apiError) {
        super(apiError);
    }
}

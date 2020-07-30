package com.du11.coreapi.dto.response;

import org.springframework.data.annotation.CreatedBy;

public class UserDTO {
    private String empno;

    @CreatedBy
    private String createdBy;
    public String getCreatedBy() {
        return createdBy;
    }
}

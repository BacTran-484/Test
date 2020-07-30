package com.du11.coreapi.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

    @NotEmpty
    @Size(max = 14)
    private String id;

    @NotEmpty
    @Size(max = 100)
    private String password;
}

package com.du11.coreapi.dto.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMainCodeRequest implements Serializable {
    private String code;
    private String name;
    private String expDate;
    private String id;
}

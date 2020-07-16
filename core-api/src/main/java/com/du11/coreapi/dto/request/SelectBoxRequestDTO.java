package com.du11.coreapi.dto.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectBoxRequestDTO {
    private String id;
    private String text;
}

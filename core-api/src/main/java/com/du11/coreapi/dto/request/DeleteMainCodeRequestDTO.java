package com.du11.coreapi.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteMainCodeRequestDTO {
    private String commonCode;
}

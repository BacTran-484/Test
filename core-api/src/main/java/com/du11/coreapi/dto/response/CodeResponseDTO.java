package com.du11.coreapi.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CodeResponseDTO implements Serializable {
    private String cdId;
    private String dtlCdId;
    private String name;
    private String expDate;
    private String dltCdNm;
}

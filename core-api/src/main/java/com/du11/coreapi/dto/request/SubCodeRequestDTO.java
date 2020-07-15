package com.du11.coreapi.dto.request;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SubCodeRequestDTO implements Serializable {
    private String cdId;
    private String dtlCdId;
    private String rgrEno;
}

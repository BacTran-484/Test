package com.du11.coreapi.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCodeResponseDTO implements Serializable {
    private String cdId;
    private String dtlCdId;
    private String rgrEno;
//    private String name;
//    private String expDate;
}

package com.du11.coreapi.dto.response;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SRUserResponseDTO implements Serializable {
    private String empno;
    private String fnm;
    private String grp;
    private String mngrRghYn;
    private String dlyn;
}

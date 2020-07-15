package com.du11.coreapi.dto.request;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SRUserRequestDTO implements Serializable {
    private String empno;
    private String fnm;

    @Size(min = 1, max = 1)
    private String grp;

    @Size(min = 1, max = 1)
    private String mngrRghYn;

    @Size(min = 1, max = 1)
    private String dlyn;
}

package com.du11.coreapi.dto.request;

import lombok.*;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {
    private String empno;
    private String fnm;
    @Size(min = 1, max = 1)
    private String grp;
    @Size(min = 1, max = 1)
    private String mngrRghYn;
    @Size(min = 1, max = 1)
    private String dlYn;


}

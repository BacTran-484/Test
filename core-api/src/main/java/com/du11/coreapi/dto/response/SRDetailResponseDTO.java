package com.du11.coreapi.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SRDetailResponseDTO {
    private String srId;
    private String srNm;
    private String biz;
    private String state;
    private String dueDate;
    private String chgMng;
    private String devMng;
    private String allocDt;
    private String strDt;
    private String endDt;
    private String memo;
}

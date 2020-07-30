package com.du11.coreapi.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationResponse<T> {

    private int limit;
    private int offset;
    private int total;
    private String fieldName;
    private String ascDesc;
    private List<T> data;
}

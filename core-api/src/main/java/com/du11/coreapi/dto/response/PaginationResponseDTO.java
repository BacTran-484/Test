package com.du11.coreapi.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResponseDTO<T> {
    private int limit;
    private int offset;
    private int total;
    private List<T> data;
}

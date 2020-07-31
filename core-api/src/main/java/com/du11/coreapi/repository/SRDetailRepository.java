package com.du11.coreapi.repository;

import com.du11.coreapi.dto.request.SRDetailRequestDTO;
import com.du11.coreapi.dto.response.SRDetailResponseDTO;
import com.du11.coreapi.entity.SRDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SRDetailRepository {
    @Mapper
    SRDetail getSrDetailBySrId(@Param("srDetailRequestDTO") SRDetailRequestDTO srDetailRequestDTO);

    @Mapper
    int updateMemo(@Param("srDetail") SRDetailResponseDTO srDetail);

//    @Mapper
//    int updateSRDetail(@Param("srDetail") SRDetailResponseDTO srDetail);
}

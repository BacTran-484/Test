package com.du11.coreapi.repository;

import com.du11.coreapi.entity.Code;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository {

    @Mapper
    int deleteSubCode(@Param("cdId") String cdId, @Param("dtlCdId") String dtlCdId);

    @Mapper
    List<Code> findMainCodeByDropdown();

    @Mapper
    Code findSubCodeForCompanyInfo(@Param("rgrEno") String rgrEno);


//    @Mapper
//    Code findSubCodeByCompany(@Param(""))

//    @Mapper
//    Code findCodeById(@Param("id") String id);
}

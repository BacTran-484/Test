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
    List<Code> findSubCodeForCompanyInfo();

    @Mapper
    List<Code> findSubCodeForUserType();

    @Mapper
    List<Code> findAllMainCode(int limit, int offset, String fieldName, String ascDesc);

    @Mapper
    List<Code> findMainCode();

    @Mapper
    int updateCode(@Param("code") Code code);

    @Mapper
    int createCode(@Param("code") Code code);

    @Mapper
    int deleteMainCode(@Param("cdId") String cdId);

    @Mapper
    Code findMainCodeById(@Param("id") String id);


}

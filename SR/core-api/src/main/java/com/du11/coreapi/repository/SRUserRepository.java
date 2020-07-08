package com.du11.coreapi.repository;

import com.du11.coreapi.entity.SRUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SRUserRepository {

    @Mapper
    SRUser findByEmpno(@Param("empno") String empno);
}

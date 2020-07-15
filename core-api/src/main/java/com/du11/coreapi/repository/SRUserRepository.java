package com.du11.coreapi.repository;

import com.du11.coreapi.dto.request.SRUserRequestDTO;
import com.du11.coreapi.dto.response.SRUserResponseDTO;
import com.du11.coreapi.entity.SRUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SRUserRepository {

    @Mapper
    List<SRUser> findUserByEmpNo(@Param("empno") String empno, @Param("limit") int limit, @Param("offset") int offset);

    @Mapper
    List<SRUser> findUserByEmpName(@Param("fnm") String fnm, @Param("limit") int limit, @Param("offset") int offset);

    @Mapper
    List<SRUser> findUserByEmpCompany(@Param("grp") String grp, @Param("limit") int limit, @Param("offset") int offset);

    @Mapper
    int updateUser(@Param("user") SRUser user);

    @Mapper
    SRUser findByEmpno(@Param("empno") String empno);

}

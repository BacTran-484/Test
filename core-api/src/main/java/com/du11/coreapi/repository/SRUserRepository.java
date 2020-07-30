package com.du11.coreapi.repository;

import com.du11.coreapi.entity.SRUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SRUserRepository{

    @Mapper
    List<SRUser> findUserBy(@Param("empno") String empno, @Param("fnm") String fnm, @Param("grp") String grp);

    @Mapper
    int updateUser(@Param("user") SRUser user);

    @Mapper
    SRUser findByEmpno(@Param("empno") String empno);

    @Mapper
    int deleteSRUserById(@Param("empno") String empno);

    @Mapper
    List<SRUser> findAll(@Param("limit") int limit, @Param("offset") int offset);

    @Mapper
    int createUser(@Param("SRUser") SRUser srUser) throws DuplicateKeyException;

}

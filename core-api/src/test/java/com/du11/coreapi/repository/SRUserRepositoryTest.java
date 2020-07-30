package com.du11.coreapi.repository;

import com.du11.coreapi.CoreApiApplication;
import com.du11.coreapi.config.DatabaseConfig;
import com.du11.coreapi.entity.SRUser;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DatabaseConfig.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
class SRUserRepositoryTest {

    @BeforeAll
    static void setUpAll() {}

    @AfterAll
    static void tearDownAll() {}

    @BeforeEach
    void setUp(){
        SRUser mockInput = new SRUser();
        mockInput.setEmpno("thao3");
        mockInput.setFnm("Thao");
        mockInput.setGrp("1");
        mockInput.setMngrRghYn("Y");
        mockInput.setDlYn("N");
        srUserRepository.createUser(mockInput);
    }

    @AfterEach
    void tearDown() {}

    @Autowired
    SRUserRepository srUserRepository;

    @Test
    void testCreateUser_InputValid_ShouldCreateNewOneInDB(){

        //Mock input entity
        SRUser mockInput = new SRUser();
        mockInput.setEmpno("thao3");
        mockInput.setFnm("Thao");
        mockInput.setGrp("1");
        mockInput.setMngrRghYn("Y");
        mockInput.setDlYn("N");

        //Run repository function
        srUserRepository.createUser(mockInput);

        //Assert expected vs actual
        SRUser actual = srUserRepository.findByEmpno("thao3");
        assertNotNull(actual);
        assertEquals("thao3", actual.getEmpno());
        assertEquals("Thao", actual.getFnm());
        assertEquals("1", actual.getGrp());
        assertEquals("Y", actual.getMngrRghYn());
        assertEquals("N", actual.getDlYn());

    }

    @Test
    void testFindAll_InputValid_ShouldReturnAllUser () {
        List<SRUser> lstActual = srUserRepository.findAll(1,0);
        assertNotNull(lstActual);
        assertEquals(1, lstActual.size());
        assertEquals("thao3", lstActual.get(0).getEmpno());
    }

    @Test
    void testUpdateUser_InputValid_ShouldUpdateUser() {
        SRUser mockInput = new SRUser();
        mockInput.setEmpno("string");
        mockInput.setFnm("string2");
        mockInput.setGrp("3");
        mockInput.setMngrRghYn("Y");
        mockInput.setDlYn("N");

        srUserRepository.updateUser(mockInput);

        SRUser actual = srUserRepository.findByEmpno("string");
        assertNotNull(actual);
        assertEquals("string", actual.getEmpno());
        assertEquals("string2", actual.getFnm());
        assertEquals("3", actual.getGrp());
        assertEquals("Y", actual.getMngrRghYn());
        assertEquals("N", actual.getDlYn());

    }

    @Test
    void testFindUserBy_InputValid_ShouldFindUsers() {
        List<SRUser> listActual = srUserRepository.findUserBy("D00001", "user1", "1");
        assertNotNull(listActual);
        assertEquals(1, listActual.size());
        assertEquals("D00001", listActual.get(0).getEmpno());
        assertEquals("user1", listActual.get(0).getFnm());
        assertEquals("1", listActual.get(0).getGrp());
    }

}

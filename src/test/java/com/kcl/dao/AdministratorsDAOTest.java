package com.kcl.dao;

import com.kcl.constant.IdentityEnum;
import com.kcl.po.Administrator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
class AdministratorsDAOTest {

    @Autowired
    AdministratorsDAO dao;

    @Test
    void selectAdministratorByUserName() {
        Administrator admin = dao.selectAdministratorByUserName("admin");
        assert admin.getUsername().equals("admin");
    }

    @Test
    void getPassword() {
        System.out.println(dao.getPassword("admin"));
    }

    @Test
    void updatePassword() {
        assert dao.updatePassword("admin", "123456") > 0;
    }

}
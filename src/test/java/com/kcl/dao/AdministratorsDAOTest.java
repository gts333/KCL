package com.kcl.dao;

import com.kcl.constant.IdentityEnum;
import com.kcl.domain.Administrator;
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
    void updateAdministrator() {
        Administrator administrator = new Administrator();
        administrator.setUserId(1);
        administrator.setIdentity(IdentityEnum.ADMINISTRATOR);
        administrator.setPassword("");
        administrator.setUsername("");
        assert dao.updateAdministrator(administrator) > 0;
    }


    @Test
    void selectAllAdministrators() {
        List<Administrator> admins = dao.selectAllAdministrators();
        assert admins.size() > 0;
    }

    @Test
    void selectAdministratorByUserId() {
        Administrator admin = dao.selectAdministratorByUserId(1);
        assert admin != null;
    }

    @Test
    void selectAdministratorByUserName() {
        Administrator admin = dao.selectAdministratorByUserName("admin");
        assert admin != null;
    }


    @Test
    void administratorLogin() {
        int count = dao.administratorLogin("admin", "123456");
        assert count > 0;
    }

}
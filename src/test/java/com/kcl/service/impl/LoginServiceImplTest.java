package com.kcl.service.impl;

import com.kcl.component.PasswordManager;
import com.kcl.constant.IdentityEnum;
import com.kcl.dao.AdministratorsDAO;
import com.kcl.dao.StudentsDAO;
import com.kcl.dao.TeachingAssistantsDAO;
import com.kcl.po.Administrator;
import com.kcl.po.TeachingAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceImplTest {

    @Autowired
    LoginServiceImpl loginService;
    @Autowired
    AdministratorsDAO administratorsDAO;
    @Autowired
    StudentsDAO studentsDAO;
    @Autowired
    TeachingAssistantsDAO teachingAssistantsDAO;
    @Autowired
    PasswordManager passwordManager;

    @Test
    void general() {
        administratorsDAO.updateAdministrator(new Administrator(1, "admin", passwordManager.encode("123456"), IdentityEnum.ADMINISTRATOR));

//        teachingAssistantsDAO.updateTeachingAssistant(new TeachingAssistant(3, "ta_3", passwordManager.encode("123456"),
//                IdentityEnum.TEACHING_ASSISTANT, true, true));
    }


}
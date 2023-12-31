package com.kcl.dao;


import com.kcl.constant.IdentityEnum;
import com.kcl.po.TeachingAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TeachingAssistantsDAOTest {
    @Autowired
    TeachingAssistantsDAO dao;

    @Test
    public void addTeachingAssistant() {
        assert dao.addTeachingAssistant(new TeachingAssistant("username", "passowr", IdentityEnum.TEACHING_ASSISTANT, true, true)) > 0;
    }

    @Test
    public void removeTeachingAssistant() {
        assert dao.removeTeachingAssistant("alpha") > 0;
    }

    @Test
    public void updateTeachingAssistant() {
        assert dao.updateTeachingAssistant(new TeachingAssistant("alpha", "passowrd", IdentityEnum.TEACHING_ASSISTANT, true, true)) > 0;
    }

    @Test
    public void selectAllTeachingAssistants() {
        System.out.println(dao.selectAllTeachingAssistants());
    }

    @Test
    public void getPassword() {
        System.out.println(dao.getPassword("ta_1"));
    }

    @Test
    void updatePassword() {
        assert dao.updatePassword("alpha", "123456") > 0;
    }

}
package com.kcl.dao;

import com.kcl.po.TeachingAssistantAvailableTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TeachingAssistantAvailableTimesDAOTest {

    @Autowired
    TeachingAssistantAvailableTimesDAO dao;

    @Test
    public void addTeachingAssistantAvailableTime() {
        assert dao.addTeachingAssistantAvailableTime(new TeachingAssistantAvailableTime(20, "uname", "02_02_02", "hi", true )) > 0;
    }

    @Test
    public void removeTeachingAssistantAvailableTime() {
        assert dao.removeTeachingAssistantAvailableTime(12) > 0;
    }

    @Test
    public void updateTeachingAssistantAvailableTime() {
        assert dao.updateTeachingAssistantAvailableTime(new TeachingAssistantAvailableTime(3, "alpha", "02_02_02", "hi", true )) > 0;

    }

    @Test
    void selectTeachingAssistantAllTimesByTeachingAssistantUsername() {
        assert dao.selectTeachingAssistantAllTimesByTeachingAssistantUsername("alpha").size() > 0;
    }
}
package com.kcl.dao;

import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.dto.TimeSlot;
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
        assert dao.addTeachingAssistantAvailableTime(new TeachingAssistantAvailableTime(20, 1, new TimeSlot("02_02_02"), 0, true )) > 0;
    }

    @Test
    public void removeTeachingAssistantAvailableTime() {
        assert dao.removeTeachingAssistantAvailableTime(12) > 0;
    }

    @Test
    public void updateTeachingAssistantAvailableTime() {
        assert dao.updateTeachingAssistantAvailableTime(new TeachingAssistantAvailableTime(3, 1, new TimeSlot("04_14_02"), 0, true )) > 0;

    }

    @Test
    public void selectTeachingAssistantAllTimesByTeachingAssistantUserId() {
        System.out.println(dao.selectTeachingAssistantAllTimesByTeachingAssistantUserId(1));
    }

    @Test
    public void selectTeachingAssistantAvailableTimesByTeachingAssistantUserId() {
        System.out.println(dao.selectTeachingAssistantAvailableTimesByTeachingAssistantUserId(1));

    }
}
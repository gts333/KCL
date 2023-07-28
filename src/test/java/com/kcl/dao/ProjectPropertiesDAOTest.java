package com.kcl.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ProjectPropertiesDAOTest {
    @Autowired
    ProjectPropertiesDAO dao;

    @Test
    void getAutoTeachingAssistantAllocationEnabled() {
        System.out.println(dao.getAutoTeachingAssistantAllocationEnabled());
    }

    @Test
    void getAmountToTriggerAutoAllocation() {
        System.out.println(dao.getAmountToTriggerAutoAllocation());
    }

    @Test
    void updateAutoTeachingAssistantAllocationEnabled() {
        dao.updateAutoTeachingAssistantAllocationEnabled(true);
    }

    @Test
    void updateAmountToTriggerAutoAllocation() {
        dao.updateAmountToTriggerAutoAllocation(10);
    }



    @Test
    void getDefaultTime() {
        System.out.println(dao.getDefaultTime());
    }

    @Test
    void updateDefaultTime() {
        dao.updateDefaultTime(456);
    }
}
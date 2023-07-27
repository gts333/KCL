package com.kcl.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ProjectPropertiesServiceTest {

    @Autowired
    ProjectPropertiesService service;

    @Test
    void getAutoTeachingAssistantAllocationEnabled() {
        assert service.getAutoTeachingAssistantAllocationEnabled();
    }

    @Test
    void getAmountToTriggerAutoAllocation() {
        System.out.println(service.getAmountToTriggerAutoAllocation());
    }

    @Test
    void updateAutoTeachingAssistantAllocationEnabled() {
        service.updateAutoTeachingAssistantAllocationEnabled(true);
    }

    @Test
    void updateAmountToTriggerAutoAllocation() {
        service.updateAmountToTriggerAutoAllocation(4);
    }

    @Test
    void getSettings() {
        System.out.println(service.getSettings());
    }
}
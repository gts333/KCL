package com.kcl.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class AutomatedTeachingAssistantUpdateServiceTest {
    @Autowired
    AutomatedTeachingAssistantUpdateService service;

    @Test
    void checkAndUpdateTeachingAssistantResourceGroup() {
        service.checkAndUpdateTeachingAssistantResourceGroup();
    }

    @Test
    void updateTeachingAssistantAvailabilityStatus() {
        service.updateTeachingAssistantAvailabilityStatus();
    }

    @Test
    void updateUnreachableTeachingAssistantAvailableTimes() {
        service.updateUnreachableTeachingAssistantAvailableTimes();
    }

    @Test
    void refreshTeachingAssistantAvailabilityStatus() {
        service.refreshTeachingAssistantAvailabilityStatus();
    }
}
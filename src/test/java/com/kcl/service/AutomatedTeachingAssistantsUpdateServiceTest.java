package com.kcl.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AutomatedTeachingAssistantsUpdateServiceTest {
    @Autowired
    AutomatedTeachingAssistantsUpdateService service;

    @Test
    void checkAndUpdateTeachingAssistantResourceGroup() {
        service.checkAndAddTeachingAssistantResourceGroup();
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

    @Test
    void checkAndRemoveTeachingAssistantResourceGroup() {
        service.checkAndRemoveTeachingAssistantResourceGroup();
    }
}
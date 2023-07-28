package com.kcl.service.impl;

import com.kcl.service.AutomatedTeachingAssistantUpdateService;
import org.springframework.stereotype.Service;

@Service
public class AutomatedTeachingAssistantUpdateServiceImpl implements AutomatedTeachingAssistantUpdateService {

    @Override
    public void checkAndUpdateTeachingAssistantResourceGroup() {
        System.out.println("a");
    }

    @Override
    public void updateTeachingAssistantAvailabilityStatus() {
        System.out.println("b");
    }

    @Override
    public void updateUnreachableTeachingAssistantAvailableTimes() {
        System.out.println("c");
    }

    @Override
    public void refreshTeachingAssistantAvailabilityStatus() {
        System.out.println("d");
    }
}

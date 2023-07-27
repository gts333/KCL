package com.kcl.service;

import com.kcl.dto.SettingsDTO;

import java.util.List;

public interface ProjectPropertiesService {

    boolean getAutoTeachingAssistantAllocationEnabled();

    int getAmountToTriggerAutoAllocation();

    int getPriorityTime();

    void updateAutoTeachingAssistantAllocationEnabled(boolean b);

    void updateAmountToTriggerAutoAllocation(int value);

    void updatePriorityTime(int time);

    List<SettingsDTO> getSettings();
}

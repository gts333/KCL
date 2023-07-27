package com.kcl.service;

import com.kcl.dto.SettingsDTO;

import java.util.List;

public interface ProjectPropertiesService {

    boolean getAutoTeachingAssistantAllocationEnabled();

    int getAmountToTriggerAutoAllocation();

    void updateAutoTeachingAssistantAllocationEnabled(boolean b);

    void updateAmountToTriggerAutoAllocation(int value);

    List<SettingsDTO> getSettings();
}

package com.kcl.service.impl;

import com.kcl.dao.ProjectPropertiesDAO;
import com.kcl.dto.SettingsDTO;
import com.kcl.service.ProjectPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectPropertiesServiceImpl implements ProjectPropertiesService {

    private ProjectPropertiesDAO projectPropertiesDAO;

    @Autowired
    public ProjectPropertiesServiceImpl(ProjectPropertiesDAO projectPropertiesDAO) {
        this.projectPropertiesDAO = projectPropertiesDAO;
    }

    @Override
    public boolean getAutoTeachingAssistantAllocationEnabled() {
        return projectPropertiesDAO.getAutoTeachingAssistantAllocationEnabled();
    }

    @Override
    public int getAmountToTriggerAutoAllocation() {
        return projectPropertiesDAO.getAmountToTriggerAutoAllocation();
    }

    @Override
    public void updateAutoTeachingAssistantAllocationEnabled(boolean b) {
        projectPropertiesDAO.updateAutoTeachingAssistantAllocationEnabled(b);
    }

    @Override
    public void updateAmountToTriggerAutoAllocation(int value) {
        projectPropertiesDAO.updateAmountToTriggerAutoAllocation(value);
    }

    @Override
    public List<SettingsDTO> getSettings() {
        List<SettingsDTO> list = new ArrayList<>();
        list.add(new SettingsDTO("autoTeachingAssistantAllocationEnabled", String.valueOf(projectPropertiesDAO.getAutoTeachingAssistantAllocationEnabled())));
        list.add(new SettingsDTO("amountToTriggerAutoAllocation", String.valueOf(projectPropertiesDAO.getAmountToTriggerAutoAllocation())));
        return list;
    }
}

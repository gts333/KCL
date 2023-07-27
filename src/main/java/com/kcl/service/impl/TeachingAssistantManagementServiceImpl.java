package com.kcl.service.impl;

import com.kcl.component.PasswordManager;
import com.kcl.dao.TeachingAssistantAvailableTimesDAO;
import com.kcl.dao.TeachingAssistantResourceGroupsDAO;
import com.kcl.dao.TeachingAssistantsDAO;
import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.po.ResourceGroup;
import com.kcl.po.TeachingAssistant;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.po.TeachingAssistantResourceGroup;
import com.kcl.service.TeachingAssistantManagementService;
import com.kcl.util.TimeIntervalCalculatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeachingAssistantManagementServiceImpl implements TeachingAssistantManagementService {

    private TeachingAssistantsDAO teachingAssistantsDAO;
    private TeachingAssistantAvailableTimesDAO teachingAssistantAvailableTimesDAO;
    private TeachingAssistantResourceGroupsDAO teachingAssistantResourceGroupsDAO;
    private PasswordManager passwordManager;

    @Autowired
    public TeachingAssistantManagementServiceImpl(TeachingAssistantsDAO teachingAssistantsDAO,
                                                  TeachingAssistantAvailableTimesDAO teachingAssistantAvailableTimesDAO,
                                                  TeachingAssistantResourceGroupsDAO teachingAssistantResourceGroupsDAO,
                                                  PasswordManager passwordManager) {
        this.teachingAssistantsDAO = teachingAssistantsDAO;
        this.teachingAssistantAvailableTimesDAO = teachingAssistantAvailableTimesDAO;
        this.teachingAssistantResourceGroupsDAO = teachingAssistantResourceGroupsDAO;
        this.passwordManager = passwordManager;
    }


    @Override
    public boolean addTeachingAssistant(TeachingAssistant teachingAssistant) {
        teachingAssistant.setPassword(passwordManager.encode(teachingAssistant.getPassword()));
        return teachingAssistantsDAO.addTeachingAssistant(teachingAssistant) > 0;
    }

    @Override
    public boolean removeTeachingAssistant(String username) {
        return teachingAssistantsDAO.removeTeachingAssistant(username) > 0;
    }

    @Override
    public boolean updateTeachingAssistant(TeachingAssistant teachingAssistant) {
        return teachingAssistantsDAO.updateTeachingAssistant(teachingAssistant) > 0;
    }

    @Override
    public List<TeachingAssistant> selectAllTeachingAssistants() {
        return teachingAssistantsDAO.selectAllTeachingAssistants();
    }

    @Override
    public boolean addTeachingAssistantResourceGroup(TeachingAssistantResourceGroup teachingAssistantResourceGroup) {
        return teachingAssistantResourceGroupsDAO.addTeachingAssistantResourceGroup(teachingAssistantResourceGroup) > 0;
    }

    @Override
    public boolean removeTeachingAssistantResourceGroup(TeachingAssistantResourceGroup teachingAssistantResourceGroup) {
        return teachingAssistantResourceGroupsDAO.removeTeachingAssistantResourceGroup(teachingAssistantResourceGroup) > 0;
    }

    @Override
    public List<TeachingAssistantResourceGroup> selectTeachingAssistantResourceGroupByUsername(String username) {
        return teachingAssistantResourceGroupsDAO.selectTeachingAssistantResourceGroupByUsername(username);
    }

    @Override
    public boolean addTeachingAssistantAvailableTime(TeachingAssistantAvailableTime teachingAssistantAvailableTime) {
        return teachingAssistantAvailableTimesDAO.addTeachingAssistantAvailableTime(teachingAssistantAvailableTime) > 0;
    }

    @Override
    public boolean removeTeachingAssistantAvailableTime(int timeId) {
        return teachingAssistantAvailableTimesDAO.removeTeachingAssistantAvailableTime(timeId) > 0;
    }

    @Override
    public boolean updateTeachingAssistantAvailableTime(TeachingAssistantAvailableTime teachingAssistantAvailableTime) {
        return teachingAssistantAvailableTimesDAO.updateTeachingAssistantAvailableTime(teachingAssistantAvailableTime) > 0;
    }

    @Override
    public List<TeachingAssistantAvailableTime> selectTeachingAssistantAllTimesByTeachingAssistantUsername(String username) {
        return teachingAssistantAvailableTimesDAO.selectTeachingAssistantAllTimesByTeachingAssistantUsername(username);
    }

    @Override
    public List<TeachingAssistantDTO> selectAllTeachingAssistantDTOs() {
        List<TeachingAssistant> teachingAssistants = teachingAssistantsDAO.selectAllTeachingAssistants();
        List<TeachingAssistantDTO> entities = new ArrayList<>();
        for (TeachingAssistant teachingAssistant : teachingAssistants) {
            String username = teachingAssistant.getUsername();
            List<TeachingAssistantResourceGroup> groups = teachingAssistantResourceGroupsDAO.selectTeachingAssistantResourceGroupByUsername(username);
            List<TeachingAssistantAvailableTime> teachingAssistantAvailableTimes =
                    teachingAssistantAvailableTimesDAO.selectTeachingAssistantAllTimesByTeachingAssistantUsername(username);
            entities.add(new TeachingAssistantDTO(teachingAssistant, groups, teachingAssistantAvailableTimes));
        }
        return entities;
    }

}

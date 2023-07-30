package com.kcl.service;

import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.po.TeachingAssistant;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.po.TeachingAssistantResourceGroup;

import java.util.List;

public interface TeachingAssistantsManagementService {

    boolean addTeachingAssistant(TeachingAssistant teachingAssistant);

    boolean removeTeachingAssistant(String username);

    //this method will not update password
    boolean updateTeachingAssistant(TeachingAssistant teachingAssistant);

    List<TeachingAssistant> selectAllTeachingAssistants();

    boolean addTeachingAssistantResourceGroup(TeachingAssistantResourceGroup teachingAssistantResourceGroup);

    boolean deleteAllTeachingAssistantResourceGroups(String username);

    boolean deleteTeachingAssistantResourceGroup(TeachingAssistantResourceGroup teachingAssistantResourceGroup);

    List<TeachingAssistantResourceGroup> selectTeachingAssistantResourceGroupByUsername(String username);

    boolean addTeachingAssistantAvailableTime(TeachingAssistantAvailableTime teachingAssistantAvailableTime);

    boolean removeTeachingAssistantAvailableTime(int timeId);

    boolean updateTeachingAssistantAvailableTime(TeachingAssistantAvailableTime teachingAssistantAvailableTime);

    List<TeachingAssistantAvailableTime> selectTeachingAssistantAllTimesByTeachingAssistantUsername(String username);

    List<TeachingAssistantDTO> selectAllTeachingAssistantDTOs();

    List<TeachingAssistantDTO> selectAllAvailableTeachingAssistantDTOsByGroupName(String groupName);
}

package com.kcl.service.impl;

import com.kcl.dao.*;
import com.kcl.po.Request;
import com.kcl.po.TeachingAssistant;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.service.AutomatedTeachingAssistantUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AutomatedTeachingAssistantUpdateServiceImpl implements AutomatedTeachingAssistantUpdateService {

    private TeachingAssistantsDAO teachingAssistantsDAO;
    private TeachingAssistantAvailableTimesDAO teachingAssistantAvailableTimesDAO;
    private TeachingAssistantResourceGroupsDAO teachingAssistantResourceGroupsDAO;
    private ProjectPropertiesDAO projectPropertiesDAO;
    private RequestsDAO requestsDAO;

    @Autowired
    public AutomatedTeachingAssistantUpdateServiceImpl(
            TeachingAssistantsDAO teachingAssistantsDAO,
            TeachingAssistantAvailableTimesDAO teachingAssistantAvailableTimesDAO,
            TeachingAssistantResourceGroupsDAO teachingAssistantResourceGroupsDAO,
            ProjectPropertiesDAO projectPropertiesDAO,
            RequestsDAO requestsDAO
    ) {
        this.teachingAssistantsDAO = teachingAssistantsDAO;
        this.teachingAssistantAvailableTimesDAO = teachingAssistantAvailableTimesDAO;
        this.teachingAssistantResourceGroupsDAO = teachingAssistantResourceGroupsDAO;
        this.projectPropertiesDAO = projectPropertiesDAO;
        this.requestsDAO = requestsDAO;
    }

    @Override
    public void checkAndUpdateTeachingAssistantResourceGroup() {
        boolean enabled = projectPropertiesDAO.getAutoTeachingAssistantAllocationEnabled();
        if (!enabled) {
            return;
        }
        int threshold = projectPropertiesDAO.getAmountToTriggerAutoAllocation();
        List<Request> requests = requestsDAO.selectAllRequests();
        if (requests.size() < threshold) {
            return;
        }
        Map<String, Integer> requestsCountPerGroupMap = new HashMap<>();
        for (Request request : requests) {
            requestsCountPerGroupMap.put(request.getGroupName(), )
        }


    }

    @Override
    public void updateTeachingAssistantAvailabilityStatus() {
        List<TeachingAssistant> teachingAssistants = teachingAssistantsDAO.selectAllTeachingAssistants();
        for (TeachingAssistant teachingAssistant : teachingAssistants) {
            boolean originalAvailability = teachingAssistant.isAvailable();
            List<TeachingAssistantAvailableTime> teachingAssistantAvailableTimes =
                    teachingAssistantAvailableTimesDAO.selectTeachingAssistantAllTimesByTeachingAssistantUsername(teachingAssistant.getUsername());
            boolean available = false;
            for (TeachingAssistantAvailableTime time : teachingAssistantAvailableTimes) {
                if (time.isAvailable()) {
                    available = true;
                    break;
                }
            }
            if (available != originalAvailability) {
                teachingAssistant.setAvailable(available);
                teachingAssistantsDAO.updateTeachingAssistant(teachingAssistant);
            }
        }
    }

    @Override
    @Scheduled(cron = "0 0 1 * * *")
    public void updateUnreachableTeachingAssistantAvailableTimes() {
        int weekday = LocalDateTime.now().getDayOfWeek().getValue();
        List<TeachingAssistant> teachingAssistants = teachingAssistantsDAO.selectAllTeachingAssistants();
        for (TeachingAssistant teachingAssistant : teachingAssistants) {
            List<TeachingAssistantAvailableTime> teachingAssistantAvailableTimes =
                    teachingAssistantAvailableTimesDAO.selectTeachingAssistantAllTimesByTeachingAssistantUsername(teachingAssistant.getUsername());
            for (TeachingAssistantAvailableTime time : teachingAssistantAvailableTimes) {
                if (Integer.parseInt(time.getTime().substring(0, 2)) < weekday) {
                    time.setAvailable(false);
                    teachingAssistantAvailableTimesDAO.updateTeachingAssistantAvailableTime(time);
                }
            }
        }
    }

    @Override
    @Scheduled(cron = "0 0 1 * * SAT")
    public void refreshTeachingAssistantAvailabilityStatus() {
        List<TeachingAssistant> teachingAssistants = teachingAssistantsDAO.selectAllTeachingAssistants();
        for (TeachingAssistant teachingAssistant : teachingAssistants) {
            teachingAssistant.setAvailable(true);
            teachingAssistantsDAO.updateTeachingAssistant(teachingAssistant);
            List<TeachingAssistantAvailableTime> teachingAssistantAvailableTimes =
                    teachingAssistantAvailableTimesDAO.selectTeachingAssistantAllTimesByTeachingAssistantUsername(teachingAssistant.getUsername());
            for (TeachingAssistantAvailableTime time : teachingAssistantAvailableTimes) {
                time.setAvailable(true);
                teachingAssistantAvailableTimesDAO.updateTeachingAssistantAvailableTime(time);
            }
        }
    }
}

package com.kcl.service.impl;

import com.kcl.dao.*;
import com.kcl.po.*;
import com.kcl.service.AutomatedTeachingAssistantsUpdateService;
import com.kcl.service.TeachingAssistantsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AutomatedTeachingAssistantsUpdateServiceImpl implements AutomatedTeachingAssistantsUpdateService {

    private TeachingAssistantsManagementService teachingAssistantsManagementService;
    private ProjectPropertiesDAO projectPropertiesDAO;
    private ResourceGroupsDAO resourceGroupsDAO;
    private RequestsDAO requestsDAO;
    private AppointmentsDAO appointmentsDAO;

    @Autowired
    public AutomatedTeachingAssistantsUpdateServiceImpl(
            TeachingAssistantsManagementService teachingAssistantsManagementService,
            ProjectPropertiesDAO projectPropertiesDAO,
            ResourceGroupsDAO resourceGroupsDAO,
            RequestsDAO requestsDAO,
            AppointmentsDAO appointmentsDAO
    ) {
        this.teachingAssistantsManagementService = teachingAssistantsManagementService;
        this.projectPropertiesDAO = projectPropertiesDAO;
        this.resourceGroupsDAO = resourceGroupsDAO;
        this.requestsDAO = requestsDAO;
        this.appointmentsDAO = appointmentsDAO;
    }

    @Override
    public void checkAndAddTeachingAssistantResourceGroup() {
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
            String groupName = request.getGroupName();
            requestsCountPerGroupMap.put(groupName, requestsCountPerGroupMap.getOrDefault(groupName, 0) + 1);
        }
        //use a priority queue so that the groups with the most amount of requests are handled first
        Queue<Map.Entry<String, Integer>> groupsRequiringMoreTeachingAssistants = new PriorityQueue<>((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));
        for (Map.Entry<String, Integer> entry : requestsCountPerGroupMap.entrySet()) {
            if (entry.getValue() >= threshold) {
                groupsRequiringMoreTeachingAssistants.add(entry);
            }
        }
        if (groupsRequiringMoreTeachingAssistants.size() == 0) {
            return;
        }
        //the list of group names that require more TAs
        List<String> groups = groupsRequiringMoreTeachingAssistants.stream().map(Map.Entry::getKey).collect(Collectors.toList());




    }

    @Override
    @Scheduled(cron = "0 0 23 * * FRI")
    public void checkAndRemoveTeachingAssistantResourceGroup() {
        List<String> resourceGroupNames = resourceGroupsDAO.selectAllResourceGroups().stream().map(ResourceGroup::getGroupName).collect(Collectors.toList());
        List<Appointment> appointments = appointmentsDAO.selectAllAppointments();
        for (Appointment appointment : appointments) {
            resourceGroupNames.remove(appointment.getGroupName());
        }
        //now that resourceGroupNames contains all the resource groups where no appointment had been made
    }

    @Override
    public void updateTeachingAssistantAvailabilityStatus() {
        List<TeachingAssistant> teachingAssistants = teachingAssistantsManagementService.selectAllTeachingAssistants();
        for (TeachingAssistant teachingAssistant : teachingAssistants) {
            boolean originalAvailability = teachingAssistant.isAvailable();
            List<TeachingAssistantAvailableTime> teachingAssistantAvailableTimes =
                    teachingAssistantsManagementService.selectTeachingAssistantAllTimesByTeachingAssistantUsername(teachingAssistant.getUsername());
            boolean available = false;
            for (TeachingAssistantAvailableTime time : teachingAssistantAvailableTimes) {
                if (time.isAvailable()) {
                    available = true;
                    break;
                }
            }
            if (available != originalAvailability) {
                teachingAssistant.setAvailable(available);
                teachingAssistantsManagementService.updateTeachingAssistant(teachingAssistant);
            }
        }
    }

    @Override
    @Scheduled(cron = "0 0 1 * * *")
    public void updateUnreachableTeachingAssistantAvailableTimes() {
        int weekday = LocalDateTime.now().getDayOfWeek().getValue();
        List<TeachingAssistant> teachingAssistants = teachingAssistantsManagementService.selectAllTeachingAssistants();
        for (TeachingAssistant teachingAssistant : teachingAssistants) {
            List<TeachingAssistantAvailableTime> teachingAssistantAvailableTimes =
                    teachingAssistantsManagementService.selectTeachingAssistantAllTimesByTeachingAssistantUsername(teachingAssistant.getUsername());
            for (TeachingAssistantAvailableTime time : teachingAssistantAvailableTimes) {
                if (Integer.parseInt(time.getTime().substring(0, 2)) < weekday) {
                    time.setAvailable(false);
                    teachingAssistantsManagementService.updateTeachingAssistantAvailableTime(time);
                }
            }
        }
    }

    @Override
    @Scheduled(cron = "0 0 1 * * SAT")
    public void refreshTeachingAssistantAvailabilityStatus() {
        List<TeachingAssistant> teachingAssistants = teachingAssistantsManagementService.selectAllTeachingAssistants();
        for (TeachingAssistant teachingAssistant : teachingAssistants) {
            teachingAssistant.setAvailable(true);
            teachingAssistantsManagementService.updateTeachingAssistant(teachingAssistant);
            List<TeachingAssistantAvailableTime> teachingAssistantAvailableTimes =
                    teachingAssistantsManagementService.selectTeachingAssistantAllTimesByTeachingAssistantUsername(teachingAssistant.getUsername());
            for (TeachingAssistantAvailableTime time : teachingAssistantAvailableTimes) {
                time.setAvailable(true);
                teachingAssistantsManagementService.updateTeachingAssistantAvailableTime(time);
            }
        }
    }
}

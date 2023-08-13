package com.kcl.service.impl;


import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.po.*;
import com.kcl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AutomatedTeachingAssistantsUpdateServiceImpl implements AutomatedTeachingAssistantsUpdateService {

    private TeachingAssistantsManagementService teachingAssistantsManagementService;
    private ProjectPropertiesService projectPropertiesService;
    private ResourceGroupsService resourceGroupsService;
    private RequestsService requestsService;
    private AppointmentsService appointmentsService;
    private NotificationService notificationService;

    @Autowired
    public AutomatedTeachingAssistantsUpdateServiceImpl(
            TeachingAssistantsManagementService teachingAssistantsManagementService,
            ProjectPropertiesService projectPropertiesService,
            ResourceGroupsService resourceGroupsService,
            RequestsService requestsService,
            AppointmentsService appointmentsService,
            NotificationService notificationService
    ) {
        this.teachingAssistantsManagementService = teachingAssistantsManagementService;
        this.projectPropertiesService = projectPropertiesService;
        this.resourceGroupsService = resourceGroupsService;
        this.requestsService = requestsService;
        this.appointmentsService = appointmentsService;
        this.notificationService = notificationService;
    }

    @Override
    public void checkAndAddTeachingAssistantResourceGroup() {
        boolean enabled = projectPropertiesService.getAutoTeachingAssistantAllocationEnabled();
        if (!enabled) {
            return;
        }
        int threshold = projectPropertiesService.getAmountToTriggerAutoAllocation();
        List<Request> requests = requestsService.selectAllRequests();
        int amountOfRequests = requests.size();
        if (amountOfRequests < threshold) {
            return;
        }
        Map<String, Integer> map = new HashMap<>();
        for (Request request : requests) {
            String groupName = request.getGroupName();
            map.put(groupName, map.getOrDefault(groupName, 0) + 1);
        }
        String groupNeedsHelp = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            //because we are checking the queue each time a request is added, so there should be only at most one
            //group whose amount of requests reaches the threshold.
            if (entry.getValue() == threshold) {
                groupNeedsHelp = entry.getKey();
                break;
            }
        }
        if (groupNeedsHelp.equals("")) {
            return;
        }
        String groupProvidesHelp = selectGroupNameWithMostAmountOfAdjustableOfficeHours();
        List<TeachingAssistantDTO> teachingAssistantDTOS = teachingAssistantsManagementService.selectAllAvailableTeachingAssistantDTOsByGroupName(groupProvidesHelp);
        for (TeachingAssistantDTO dto : teachingAssistantDTOS) {
            if (!dto.isAdjustable() || dto.getResourceGroupNames().contains(groupNeedsHelp)) {
                continue;
            }
            teachingAssistantsManagementService.addTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup(dto.getUsername(), groupNeedsHelp, new Timestamp(System.currentTimeMillis())));
        }
        int currentAmountOfRequests = requests.size();
        if (currentAmountOfRequests == amountOfRequests) {
            String message = "The following resource group: " + groupNeedsHelp + "needs more TAs";
            notificationService.sendMessage(message);
        }
    }

    private String selectGroupNameWithMostAmountOfAdjustableOfficeHours() {
        Map<String, Integer> map = new HashMap<>();
        List<String> groupNames = resourceGroupsService.selectAllResourceGroups().stream().map(ResourceGroup::getGroupName).collect(Collectors.toList());
        for (String groupName : groupNames) {
            List<TeachingAssistantDTO> teachingAssistantDTOS = teachingAssistantsManagementService.selectAllAvailableTeachingAssistantDTOsByGroupName(groupName);
            for (TeachingAssistantDTO dto: teachingAssistantDTOS) {
                if (!dto.isAdjustable()) {
                    continue;
                }
                List<TeachingAssistantAvailableTime> times = dto.getTimes();
                for (TeachingAssistantAvailableTime time : times) {
                    if (time.isAvailable()) {
                        map.put(groupName, map.getOrDefault(groupName, 0) + 1);
                    }
                }
            }
        }
        int max = 0;
        String groupName = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if (val > max) {
                max = val;
                groupName = entry.getKey();
            }
        }
        return groupName;
    }

    @Override
    @Scheduled(cron = "0 0 23 * * FRI")
    public void checkAndRemoveTeachingAssistantResourceGroup() {
        List<String> resourceGroupNamesToRemove = resourceGroupsService.selectAllResourceGroups().stream().map(ResourceGroup::getGroupName).collect(Collectors.toList());
        List<Appointment> appointments = appointmentsService.selectAllAppointments();
        for (Appointment appointment : appointments) {
            resourceGroupNamesToRemove.remove(appointment.getGroupName());
        }
        //now that resourceGroupNamesToRemove contains all the resource groups where no appointment had been made
        if (resourceGroupNamesToRemove.size() == 0) {
            return;
        }
        List<TeachingAssistantDTO> teachingAssistantDTOS = teachingAssistantsManagementService.selectAllTeachingAssistantDTOs();
        //we want to remove these resource groups from all TAs
        for (TeachingAssistantDTO dto : teachingAssistantDTOS) {
            List<String> teachingAssistantGroupNames = dto.getResourceGroupNames();
            /*
            This is a vital step to ensure we only remove the resource groups that are added to the TA due to resource allocation mechanism,
            while keeping the original resource group that TA initially belongs to. Because the teachingAssistantGroupNames list is retrieved
            from the database based on their creation time, the first element of this list is the initial resource group that TA belongs to.
            We must not remove this resource group from the TA.
             */
            if (teachingAssistantGroupNames.size() <= 1) {
                continue;
            }
            teachingAssistantGroupNames.remove(0);
            Iterator<String> teachingAssistantGroupNamesIterator = teachingAssistantGroupNames.iterator();
            while (teachingAssistantGroupNamesIterator.hasNext()) {
                String groupName = teachingAssistantGroupNamesIterator.next();
                //if the TA's resource groups contains the group that should be removed, and this is not the initial resource group for this TA
                if (resourceGroupNamesToRemove.contains(groupName) && teachingAssistantGroupNames.size() > 1) {
                    //we no longer let that TA belongs to this resource group
                    teachingAssistantsManagementService.deleteTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup(dto.getUsername(), groupName));
                    teachingAssistantGroupNamesIterator.remove();
                }
            }
        }
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

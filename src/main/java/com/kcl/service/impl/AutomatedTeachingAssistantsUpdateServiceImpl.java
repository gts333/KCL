package com.kcl.service.impl;


import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.po.*;
import com.kcl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
        if (requests.size() < threshold) {
            return;
        }
        Map<String, Integer> requestsCountPerGroupMap = new HashMap<>();
        List<ResourceGroup> resourceGroups = resourceGroupsService.selectAllResourceGroups();
        for (ResourceGroup resourceGroup : resourceGroups) {
            String groupName = resourceGroup.getGroupName();
            requestsCountPerGroupMap.put(groupName, requestsService.selectAmountOfRequestsByGroupName(groupName));
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
        //we handle each group one by one
        List<TeachingAssistantDTO> teachingAssistantDTOS = teachingAssistantsManagementService.selectAllTeachingAssistantDTOs();
        for (String groupRequiringMoreTAs : groups) {
            int currentAmountOfRequests = requestsService.selectAmountOfRequestsByGroupName(groupRequiringMoreTAs);
            for (TeachingAssistantDTO dto : teachingAssistantDTOS) {
                if (!dto.isAvailable() || !dto.isAdjustable() || dto.getResourceGroupNames().contains(groupRequiringMoreTAs)) {
                    continue;
                }
                /*
                the execution of "addTeachingAssistantResourceGroup" invokes "checkAndUpdateRequestQueue"
                method in "AutomatedRequestsAndAppointmentsUpdateService" immediately, please refer to
                "AddTeachingAssistantResourceGroupAspect" and "AutomatedRequestsAndAppointmentsUpdateServiceImpl"
                for details
                 */
                teachingAssistantsManagementService.addTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup(dto.getUsername(), groupRequiringMoreTAs));
                /*
                After the implicit invocation of the checkAndUpdateRequestQueue method,
                we now check whether the amount of requests in that resource group fall below the threshold
                 */
                currentAmountOfRequests = requestsService.selectAmountOfRequestsByGroupName(groupRequiringMoreTAs);
                /*
                If some requests have been dealt with, resulting in the amount of requests in that resource group falling
                below the threshold, we now move on to deal with the next resource group;
                Else we keep adding more TAs to this resource group until whether we run out of TAs or the amount of requests
                fall below the threshold
                 */
                if (currentAmountOfRequests < threshold) {
                    break;
                }
            }
            //if the automated resource allocation mechanism fails to reduce the number of requests below the threshold, warn the administrator
            if (currentAmountOfRequests >= threshold) {
                String message = "The following resource group: " + groupRequiringMoreTAs
                        + "require more TAs. There are currently more than " + threshold + " requests that cannot be fulfilled.";
                notificationService.sendMessage(message);
            }
        }
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

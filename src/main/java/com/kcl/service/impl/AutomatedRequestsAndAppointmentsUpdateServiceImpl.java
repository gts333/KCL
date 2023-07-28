package com.kcl.service.impl;


import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.po.Appointment;
import com.kcl.po.Request;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.service.AppointmentsService;
import com.kcl.service.AutomatedRequestsAndAppointmentsUpdateService;
import com.kcl.service.RequestsService;
import com.kcl.service.TeachingAssistantsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutomatedRequestsAndAppointmentsUpdateServiceImpl implements AutomatedRequestsAndAppointmentsUpdateService {

    private RequestsService requestsService;
    private AppointmentsService appointmentsService;
    private TeachingAssistantsManagementService teachingAssistantsManagementService;

    @Autowired
    public AutomatedRequestsAndAppointmentsUpdateServiceImpl(RequestsService requestsService, AppointmentsService appointmentsService, TeachingAssistantsManagementService teachingAssistantsManagementService) {
        this.requestsService = requestsService;
        this.appointmentsService = appointmentsService;
        this.teachingAssistantsManagementService = teachingAssistantsManagementService;
    }

    @Override
    @Scheduled(cron = "0 0 1 * * SAT")
    public void removeObsoleteRequestsAndAppointments() {
        List<Request> requests = requestsService.selectAllRequests();
        List<Appointment> appointments = appointmentsService.selectAllAppointments();
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        for (Request request : requests) {
            if (request.getCreationTime().compareTo(currentTimestamp) < 0) {
                requestsService.removeRequest(request.getRequestId());
            }
        }
        for (Appointment appointment : appointments) {
            if (appointment.getCreationTime().compareTo(currentTimestamp) < 0) {
                appointmentsService.removeAppointment(appointment.getAppointmentId());
            }
        }
    }

    @Override
    public void checkAndUpdateRequestQueue() {
        List<Request> requests = requestsService.selectAllRequests();
        for (Request request : requests) {
            String groupName = request.getGroupName();
            int requiredAmountOfContiguousIntervals = request.getTimeIntervals();
            List<TeachingAssistantDTO> teachingAssistantDTOs = teachingAssistantsManagementService.selectAllAvailableTeachingAssistantDTOsByGroupName(groupName);
            for (TeachingAssistantDTO dto : teachingAssistantDTOs) {
                List<TeachingAssistantAvailableTime> times = dto.getTimes();
                //a list to record possible time intervals that satisfy the request
                List<TeachingAssistantAvailableTime> appointmentTimes = new ArrayList<>();
                //we want to look for contiguous time intervals so we need to record the previous one
                String previousTimeInterval = "";
                for (TeachingAssistantAvailableTime currentTime : times) {
                    if (!currentTime.isAvailable()) {
                        continue;
                    }
                    /*
                    either the current time interval is the first time interval of a TA, or it is not contiguous with the previous time interval,
                    clear the list and add the current time interval
                     */
                    if (previousTimeInterval.equals("") || !isContiguous(previousTimeInterval, currentTime.getTime())) {
                        appointmentTimes.clear();
                        appointmentTimes.add(currentTime);
                    }
                    previousTimeInterval = currentTime.getTime();
                    /*
                    when a satisfiable request is discovered:
                    set the times' availabilities to false, create an appointment, remove the request,
                     */
                    if (appointmentTimes.size() == requiredAmountOfContiguousIntervals) {
                        for (TeachingAssistantAvailableTime appointmentTime : appointmentTimes) {
                            appointmentTime.setAvailable(false);
                            teachingAssistantsManagementService.updateTeachingAssistantAvailableTime(appointmentTime);
                        }
                        Appointment appointment = new Appointment.AppointmentBuilder()
                                .buildId(request.getStudentUsername(), currentTime.getUsername(), request.getGroupName())
                                .buildContents(request.getTitle(), request.getContent(), request.getAppointmentType())
                                .buildTime(appointmentTimes.get(0).getTime(), appointmentTimes.get(appointmentTimes.size() - 1).getTime(), new Timestamp(System.currentTimeMillis()))
                                .build();
                        appointmentsService.addAppointment(appointment);
                        requestsService.removeRequest(request.getRequestId());
                        return;
                    }
                }
            }
        }
    }

    private boolean isContiguous(String previousTimeInterval, String currentTimeInterval) {
        int previousDay = Integer.parseInt(previousTimeInterval.substring(0, 2));
        int previousHour = Integer.parseInt(previousTimeInterval.substring(3, 5));
        int previousTenMinuteInterval = Integer.parseInt(previousTimeInterval.substring(6, 8));
        int currentDay = Integer.parseInt(currentTimeInterval.substring(0, 2));
        int currentHour = Integer.parseInt(currentTimeInterval.substring(3, 5));
        int currentTenMinuteInterval = Integer.parseInt(currentTimeInterval.substring(6, 8));
        if (previousDay == currentDay) {
            if (previousHour == currentHour && previousTenMinuteInterval + 1 == currentTenMinuteInterval) {
                return true;
            } else if (previousHour + 1 == currentHour && previousTenMinuteInterval == 6 && currentTenMinuteInterval == 1) {
                return true;
            }
        }
        return false;
    }
}

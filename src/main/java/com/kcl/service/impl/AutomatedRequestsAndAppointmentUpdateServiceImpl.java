package com.kcl.service.impl;

import com.kcl.dao.AppointmentsDAO;
import com.kcl.dao.RequestsDAO;
import com.kcl.dao.TeachingAssistantAvailableTimesDAO;
import com.kcl.dao.TeachingAssistantsDAO;
import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.po.Appointment;
import com.kcl.po.Request;
import com.kcl.po.TeachingAssistant;
import com.kcl.service.AutomatedRequestsAndAppointmentUpdateService;
import com.kcl.service.TeachingAssistantManagementService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AutomatedRequestsAndAppointmentUpdateServiceImpl implements AutomatedRequestsAndAppointmentUpdateService {

    private RequestsDAO requestsDAO;
    private AppointmentsDAO appointmentsDAO;
    private TeachingAssistantManagementService teachingAssistantManagementService;

    public AutomatedRequestsAndAppointmentUpdateServiceImpl(RequestsDAO requestsDAO, AppointmentsDAO appointmentsDAO, TeachingAssistantManagementService teachingAssistantManagementService) {
        this.requestsDAO = requestsDAO;
        this.appointmentsDAO = appointmentsDAO;
        this.teachingAssistantManagementService = teachingAssistantManagementService;
    }

    @Override
    @Scheduled(cron = "0 0 1 * * SAT")
    public void removeObsoleteRequestsAndAppointments() {
        List<Request> requests = requestsDAO.selectAllRequests();
        List<Appointment> appointments = appointmentsDAO.selectAllAppointments();
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        for (Request request : requests) {
            if (request.getCreationTime().compareTo(currentTimestamp) < 0) {
                requestsDAO.removeRequest(request.getRequestId());
            }
        }
        for (Appointment appointment : appointments) {
            if (appointment.getCreationTime().compareTo(currentTimestamp) < 0) {
                appointmentsDAO.removeAppointment(appointment.getAppointmentId());
            }
        }
    }

    @Override
    public void checkAndUpdateRequestQueue() {
        List<Request> requests = requestsDAO.selectAllRequests();
        List<TeachingAssistantDTO> teachingAssistantDTOs = teachingAssistantManagementService.selectAllTeachingAssistantDTOs();

    }
}

package com.kcl.controller;

import com.kcl.constant.ProjectConstants;
import com.kcl.dto.UserDTO;
import com.kcl.po.Appointment;
import com.kcl.service.AppointmentsService;
import com.kcl.service.TeachingAssistantsManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/teachingAssistant/appointments")
public class TeachingAssistantAppointmentsController {

    private AppointmentsService appointmentsService;


    public TeachingAssistantAppointmentsController(AppointmentsService appointmentsService, TeachingAssistantsManagementService teachingAssistantsManagementService) {
        this.appointmentsService = appointmentsService;
    }

    @GetMapping("/appointments")
    public List<Appointment> appointments(HttpServletRequest request) {
        UserDTO user = (UserDTO)request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
        return appointmentsService.selectAppointmentsByTeachingAssistantUsername(user.getUsername());
    }
}

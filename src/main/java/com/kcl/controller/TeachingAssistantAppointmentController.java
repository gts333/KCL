package com.kcl.controller;

import com.kcl.constant.ProjectConstants;
import com.kcl.dto.UserDTO;
import com.kcl.po.Appointment;
import com.kcl.po.Student;
import com.kcl.service.AppointmentService;
import com.kcl.service.TeachingAssistantManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/teachingAssistant/appointments")
public class TeachingAssistantAppointmentController {

    private AppointmentService appointmentService;
    private TeachingAssistantManagementService teachingAssistantManagementService;


    public TeachingAssistantAppointmentController(AppointmentService appointmentService, TeachingAssistantManagementService teachingAssistantManagementService) {
        this.appointmentService = appointmentService;
        this.teachingAssistantManagementService = teachingAssistantManagementService;
    }

    @GetMapping("/appointments")
    public List<Appointment> appointments(HttpServletRequest request) {
        UserDTO user = (UserDTO)request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
        return null;
    }
}

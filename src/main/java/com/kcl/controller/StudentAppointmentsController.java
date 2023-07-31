package com.kcl.controller;

import com.kcl.constant.ProjectConstants;
import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.dto.UserDTO;
import com.kcl.po.Appointment;
import com.kcl.po.StudentResourceGroup;
import com.kcl.service.AppointmentsService;
import com.kcl.service.StudentsManagementService;
import com.kcl.service.TeachingAssistantsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/student/appointments")
public class StudentAppointmentsController {

    private AppointmentsService appointmentsService;

    @Autowired
    public StudentAppointmentsController(AppointmentsService appointmentsService) {
        this.appointmentsService = appointmentsService;
    }


    @GetMapping("/appointments")
    public List<Appointment> appointments(HttpServletRequest request) {
        UserDTO user = (UserDTO)request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
        return appointmentsService.selectAppointmentsByStudentUsername(user.getUsername());
    }

}

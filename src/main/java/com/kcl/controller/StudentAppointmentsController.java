package com.kcl.controller;

import com.kcl.constant.ProjectConstants;
import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.dto.UserDTO;
import com.kcl.po.Appointment;
import com.kcl.po.StudentResourceGroup;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.service.AppointmentsService;
import com.kcl.service.StudentsManagementService;
import com.kcl.service.TeachingAssistantsManagementService;
import com.kcl.util.AppointmentAnalyzerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    private TeachingAssistantsManagementService teachingAssistantsManagementService;

    @Autowired
    public StudentAppointmentsController(AppointmentsService appointmentsService, TeachingAssistantsManagementService teachingAssistantsManagementService) {
        this.appointmentsService = appointmentsService;
        this.teachingAssistantsManagementService = teachingAssistantsManagementService;
    }


    @GetMapping("/appointments")
    public List<Appointment> appointments(HttpServletRequest request) {
        UserDTO user = (UserDTO)request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
        return appointmentsService.selectAppointmentsByStudentUsername(user.getUsername());
    }

    @GetMapping("/removeAppointment")
    @Transactional
    public String removeAppointment(int appointmentId) {
        try{
            Appointment appointment = appointmentsService.selectAppointmentById(appointmentId);
            appointmentsService.removeAppointment(appointmentId);
            String teachingAssistantUsername = appointment.getTeachingAssistantUsername();
            List<TeachingAssistantAvailableTime> teachingAssistantAvailableTimes =
                    teachingAssistantsManagementService.selectTeachingAssistantAllTimesByTeachingAssistantUsername(teachingAssistantUsername);
            List<String> timeStrings = AppointmentAnalyzerUtil.generateTimeStringsOfAppointment(appointment);
            for (TeachingAssistantAvailableTime teachingAssistantAvailableTime : teachingAssistantAvailableTimes) {
                if (timeStrings.contains(teachingAssistantAvailableTime.getTime())) {
                    teachingAssistantAvailableTime.setAvailable(true);
                    teachingAssistantsManagementService.updateTeachingAssistantAvailableTime(teachingAssistantAvailableTime);
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

}

package com.kcl.controller;

import com.kcl.constant.AppointmentTypeEnum;
import com.kcl.constant.ProjectConstants;
import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.dto.UserDTO;
import com.kcl.po.Appointment;
import com.kcl.po.StudentResourceGroup;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.service.AppointmentsService;
import com.kcl.service.StudentsManagementService;
import com.kcl.service.TeachingAssistantsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student/add")
public class StudentAddNewAppointmentController {

    private StudentsManagementService studentsManagementService;
    private TeachingAssistantsManagementService teachingAssistantsManagementService;
    private AppointmentsService appointmentsService;

    @Autowired
    public StudentAddNewAppointmentController(StudentsManagementService studentsManagementService, TeachingAssistantsManagementService teachingAssistantsManagementService, AppointmentsService appointmentsService) {
        this.studentsManagementService = studentsManagementService;
        this.teachingAssistantsManagementService = teachingAssistantsManagementService;
        this.appointmentsService = appointmentsService;
    }

    @GetMapping("/resourceGroupNames")
    public List<String> resourceGroupNames(HttpServletRequest request) {
        UserDTO user = (UserDTO)request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
        List<StudentResourceGroup> studentResourceGroups = studentsManagementService.selectStudentResourceGroupsByUsername(user.getUsername());
        return studentResourceGroups.stream().map(StudentResourceGroup::getGroupName).collect(Collectors.toList());
    }

    @GetMapping("/teachingAssistantAvailableTimes")
    public List<TeachingAssistantAvailableTime> teachingAssistantAvailableTimes(String groupName) {
        return teachingAssistantsManagementService.selectAllTeachingAssistantAvailableTimesByGroupName(groupName);
    }

    @PostMapping("/addAppointment")
    public String addAppointment(HttpServletRequest request, String list, String title, String type, String description, String groupName) {
        try {
            if (list.equals("")) {
                return "please select at least one time slot";
            }
            List<Integer> timeIds = Arrays.stream(list.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            TeachingAssistantAvailableTime startTime = teachingAssistantsManagementService.selectTeachingAssistantAvailableTimeByTimeId(timeIds.get(0));
            TeachingAssistantAvailableTime endTime = teachingAssistantsManagementService.selectTeachingAssistantAvailableTimeByTimeId(timeIds.get(timeIds.size() - 1));
            for (int timeId : timeIds) {
                TeachingAssistantAvailableTime time = teachingAssistantsManagementService.selectTeachingAssistantAvailableTimeByTimeId(timeId);
                time.setAvailable(false);
                teachingAssistantsManagementService.updateTeachingAssistantAvailableTime(time);
            }
            UserDTO user = (UserDTO)request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
            Appointment appointment = new Appointment.AppointmentBuilder()
                    .buildId(user.getUsername(), startTime.getUsername(), groupName)
                    .buildTime(startTime.getTime(), endTime.getTime(), new Timestamp(System.currentTimeMillis()))
                    .buildContents(title, description, AppointmentTypeEnum.valueOf(type))
                    .build();
            appointmentsService.addAppointment(appointment);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }
}

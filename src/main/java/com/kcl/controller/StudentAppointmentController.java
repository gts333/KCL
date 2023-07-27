package com.kcl.controller;

import com.kcl.constant.PriorityStatusEnum;
import com.kcl.constant.ProjectConstants;
import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.dto.UserDTO;
import com.kcl.po.Appointment;
import com.kcl.po.ResourceGroup;
import com.kcl.po.Student;
import com.kcl.service.AppointmentService;
import com.kcl.service.StudentManagementService;
import com.kcl.service.TeachingAssistantManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//@RestController
//@RequestMapping("/students/appointments")
//public class StudentAppointmentController {
//
//    private StudentManagementService studentManagementService;
//    private TeachingAssistantManagementService teachingAssistantManagementService;
//    private AppointmentService appointmentService;
//
//    @Autowired
//    public StudentAppointmentController(StudentManagementService studentManagementService, AppointmentService appointmentService, TeachingAssistantManagementService teachingAssistantManagementService) {
//        this.studentManagementService = studentManagementService;
//        this.appointmentService = appointmentService;
//        this.teachingAssistantManagementService = teachingAssistantManagementService;
//    }
//
//    @GetMapping("/resourceGroups")
//    public List<ResourceGroup> resourceGroups(HttpServletRequest request) {
//        UserDTO user = (UserDTO)request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
//        return studentManagementService.selectResourceGroupsAccordingToUsername(user.getUsername());
//    }
//
//    @GetMapping("/teachingAssistantDTOs")
//    public List<TeachingAssistantDTO> teachingAssistantDTOs(HttpServletRequest request, int groupId) {
//        //TODO: MAKE EACH BUTTON RECEIVE INDIVIDUAL ID
//        UserDTO user = (UserDTO)request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
//        boolean isPriority = studentManagementService.selectStudentByUsername(user.getUsername()).getPriorityStatus() == PriorityStatusEnum.PRIORITY;
//        return teachingAssistantManagementService.selectAvailableTeachingAssistantDTOs(groupId, isPriority);
//    }
//
//    @PostMapping("/addAppointment")
//    public boolean addAppointment(Appointment appointment) {
//        return appointmentService.addAppointment(appointment);
//    }
//
//    @GetMapping("/appointments")
//    public List<Appointment> appointments(HttpServletRequest request) {
//        UserDTO user = (UserDTO)request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
//        Student student =  studentManagementService.selectStudentByUsername(user.getUsername());
////        return appointmentService.selectAppointmentsByStudentId(student.getUserId());
//        return null;
//    }
//}

package com.kcl.controller;

import com.kcl.constant.ProjectConstants;
import com.kcl.dto.UserDTO;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.service.TeachingAssistantsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/teachingAssistant/times")
public class TeachingAssistantTimesController {

    private TeachingAssistantsManagementService teachingAssistantsManagementService;

    @Autowired
    public TeachingAssistantTimesController(TeachingAssistantsManagementService teachingAssistantsManagementService) {
        this.teachingAssistantsManagementService = teachingAssistantsManagementService;
    }

    @GetMapping("/times")
    public List<TeachingAssistantAvailableTime> TeachingAssistantTimes(HttpServletRequest request) {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
        return teachingAssistantsManagementService.selectTeachingAssistantAllTimesByTeachingAssistantUsername(userDTO.getUsername());
    }

    @PostMapping("/addTime")
    public String addTime(HttpServletRequest request, TeachingAssistantAvailableTime time) {
        try {
            UserDTO userDTO = (UserDTO) request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
            String username = userDTO.getUsername();
            time.setUsername(username);
            teachingAssistantsManagementService.addTeachingAssistantAvailableTime(time);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @PostMapping("/updateTime")
    public String updateTime(HttpServletRequest request, TeachingAssistantAvailableTime time) {
        try {
            UserDTO userDTO = (UserDTO) request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
            String username = userDTO.getUsername();
            time.setUsername(username);
            teachingAssistantsManagementService.updateTeachingAssistantAvailableTime(time);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("/removeTime")
    public String removeTime(int timeId) {
        try {
            teachingAssistantsManagementService.removeTeachingAssistantAvailableTime(timeId);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

}

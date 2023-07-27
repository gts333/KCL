package com.kcl.controller;

import com.kcl.constant.ProjectConstants;
import com.kcl.dto.UserDTO;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.service.TeachingAssistantManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/teachingAssistant/times")
public class TeachingAssistantAvailableTimeController {

    private TeachingAssistantManagementService teachingAssistantManagementService;

    @Autowired
    public TeachingAssistantAvailableTimeController(TeachingAssistantManagementService teachingAssistantManagementService) {
        this.teachingAssistantManagementService = teachingAssistantManagementService;
    }

    @GetMapping("/teachingAssistantTimes")
    public List<TeachingAssistantAvailableTime> TeachingAssistantTimes(HttpServletRequest request) {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
        return teachingAssistantManagementService.selectTeachingAssistantAllTimesByTeachingAssistantUsername(userDTO.getUsername());
    }

    @PostMapping("/addTime")
    public boolean addTime(TeachingAssistantAvailableTime time) {
        return teachingAssistantManagementService.addTeachingAssistantAvailableTime(time);
    }

    @GetMapping("/removeTime")
    public boolean removeTime(int timeId) {
        return teachingAssistantManagementService.removeTeachingAssistantAvailableTime(timeId);
    }

    @PostMapping("/updateTime")
    public boolean updateTime(TeachingAssistantAvailableTime time) {
        return teachingAssistantManagementService.updateTeachingAssistantAvailableTime(time);
    }
}

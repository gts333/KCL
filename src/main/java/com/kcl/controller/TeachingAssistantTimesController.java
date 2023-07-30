package com.kcl.controller;

import com.kcl.constant.ProjectConstants;
import com.kcl.dto.UserDTO;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.service.TeachingAssistantsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/teachingAssistantTimes")
    public List<TeachingAssistantAvailableTime> TeachingAssistantTimes(HttpServletRequest request) {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
        return teachingAssistantsManagementService.selectTeachingAssistantAllTimesByTeachingAssistantUsername(userDTO.getUsername());
    }

    @PostMapping("/addTime")
    public boolean addTime(TeachingAssistantAvailableTime time) {
        return teachingAssistantsManagementService.addTeachingAssistantAvailableTime(time);
    }

    @GetMapping("/removeTime")
    public boolean removeTime(int timeId) {
        return teachingAssistantsManagementService.removeTeachingAssistantAvailableTime(timeId);
    }

    @PostMapping("/updateTime")
    public boolean updateTime(TeachingAssistantAvailableTime time) {
        return teachingAssistantsManagementService.updateTeachingAssistantAvailableTime(time);
    }
}

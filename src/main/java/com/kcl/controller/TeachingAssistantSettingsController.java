package com.kcl.controller;

import com.kcl.constant.ProjectConstants;
import com.kcl.dto.UserDTO;
import com.kcl.service.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/teachingAssistant/settings")
public class TeachingAssistantSettingsController  {

    private AccountManagementService accountManagementService;

    @Autowired
    public TeachingAssistantSettingsController(AccountManagementService accountManagementService) {
        this.accountManagementService = accountManagementService;
    }

    @PostMapping("/resetPassword")
    public String resetPassword(HttpServletRequest request, String originalPassword, String updatedPassword) {
        boolean b;
        try {
            UserDTO dto = (UserDTO) request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
            b = accountManagementService.updatePassword("TEACHING_ASSISTANT", dto.getUsername(), originalPassword, updatedPassword);
        } catch (Exception e) {
            return e.getMessage();
        }
        return String.valueOf(b);
    }
}

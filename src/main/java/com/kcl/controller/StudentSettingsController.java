package com.kcl.controller;

import com.kcl.constant.ProjectConstants;
import com.kcl.dto.UserDTO;
import com.kcl.service.AccountManagementService;
import com.kcl.service.ProjectPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/student/settings")
public class StudentSettingsController {

    private AccountManagementService accountManagementService;

    @Autowired
    public StudentSettingsController(AccountManagementService accountManagementService) {
        this.accountManagementService = accountManagementService;
    }

    @PostMapping("/resetPassword")
    public String resetPassword(HttpServletRequest request, String originalPassword, String updatedPassword) {
        boolean b;
        try {
            UserDTO dto = (UserDTO) request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
            b = accountManagementService.updatePassword("STUDENT", dto.getUsername(), originalPassword, updatedPassword);
        } catch (Exception e) {
            return e.getMessage();
        }
        return String.valueOf(b);
    }
}

package com.kcl.controller;

import com.kcl.service.AccountManagementService;
import com.kcl.service.ProjectPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student/settings")
public class StudentSettingsController {

    private AccountManagementService accountManagementService;

    @Autowired
    public StudentSettingsController(AccountManagementService accountManagementService) {
        this.accountManagementService = accountManagementService;
    }

    @PostMapping("/resetPassword")
    public boolean resetPassword(String identityString, String username, String originalPassword, String updatedPassword) {
        return accountManagementService.updatePassword(identityString, username, originalPassword, updatedPassword);
    }
}

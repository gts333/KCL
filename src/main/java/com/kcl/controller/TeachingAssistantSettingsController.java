package com.kcl.controller;

import com.kcl.service.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/teachingAssistant/settings")
public class TeachingAssistantSettingsController  {

    private AccountManagementService accountManagementService;

    @Autowired
    public TeachingAssistantSettingsController(AccountManagementService accountManagementService) {
        this.accountManagementService = accountManagementService;
    }

    @PostMapping("/resetPassword")
    public boolean resetPassword(String identityString, String username, String originalPassword, String updatedPassword) {
        return accountManagementService.updatePassword(identityString, username, originalPassword, updatedPassword);
    }
}

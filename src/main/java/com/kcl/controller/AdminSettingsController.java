package com.kcl.controller;


import com.kcl.dto.SettingsDTO;
import com.kcl.service.AccountManagementService;
import com.kcl.service.ProjectPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/settings")
public class AdminSettingsController {

    private ProjectPropertiesService projectPropertiesService;
    private AccountManagementService accountManagementService;

    @Autowired
    public AdminSettingsController(ProjectPropertiesService projectPropertiesService, AccountManagementService accountManagementService) {
        this.projectPropertiesService = projectPropertiesService;
        this.accountManagementService = accountManagementService;
    }

    @GetMapping
    public List<SettingsDTO> getSettings() {
        return projectPropertiesService.getSettings();
    }

    @GetMapping("/updateAutoTeachingAssistantAllocationEnabled")
    public void updateAutoTeachingAssistantAllocationEnabled(boolean b) {
        projectPropertiesService.updateAutoTeachingAssistantAllocationEnabled(b);
    }

    @GetMapping("/updateAmountToTriggerAutoAllocation")
    public void updateAmountToTriggerAutoAllocation(int i) {
        projectPropertiesService.updateAmountToTriggerAutoAllocation(i);
    }

    @PostMapping("/resetPassword")
    public boolean resetPassword(String identityString, String username, String originalPassword, String updatedPassword) {
        return accountManagementService.updatePassword(identityString, username, originalPassword, updatedPassword);
    }
}

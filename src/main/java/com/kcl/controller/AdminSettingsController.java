package com.kcl.controller;


import com.kcl.constant.ProjectConstants;
import com.kcl.dto.SettingsDTO;
import com.kcl.dto.UserDTO;
import com.kcl.service.AccountManagementService;
import com.kcl.service.ProjectPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/settings")
    public List<SettingsDTO> getSettings() {
        return projectPropertiesService.getSettings();
    }

    @GetMapping("/updateSettings")
    public String updateSettings(int option, String value) {
        try {
            switch (option) {
                case 0:
                    projectPropertiesService.updateAutoTeachingAssistantAllocationEnabled(Boolean.parseBoolean(value));
                    break;
                case 1:
                    projectPropertiesService.updateAmountToTriggerAutoAllocation(Integer.parseInt(value));
                    break;
                case 2:
                    projectPropertiesService.updateDefaultTime(Integer.parseInt(value));
                    break;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(HttpServletRequest request, String originalPassword, String updatedPassword) {
        boolean b;
        try {
            UserDTO dto = (UserDTO) request.getSession().getAttribute(ProjectConstants.SESSION_KEY);
            b = accountManagementService.updatePassword("ADMINISTRATOR", dto.getUsername(), originalPassword, updatedPassword);
        } catch (Exception e) {
            return e.getMessage();
        }
        return String.valueOf(b);
    }
}

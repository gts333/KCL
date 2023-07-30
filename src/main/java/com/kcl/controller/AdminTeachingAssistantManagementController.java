package com.kcl.controller;

import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.po.TeachingAssistant;
import com.kcl.po.TeachingAssistantResourceGroup;
import com.kcl.service.TeachingAssistantsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/teachingAssistants")
public class AdminTeachingAssistantManagementController {

    private TeachingAssistantsManagementService teachingAssistantsManagementService;

    @Autowired
    public AdminTeachingAssistantManagementController(TeachingAssistantsManagementService teachingAssistantsManagementService) {
        this.teachingAssistantsManagementService = teachingAssistantsManagementService;
    }

    @PostMapping("/addTeachingAssistantDTO")
    public String addTeachingAssistantDTO(TeachingAssistantDTO teachingAssistantDTO) {
        try {
            //for some reason the data sent from front end contains an empty resource group that should be removed
            teachingAssistantDTO.getResourceGroupNames().remove(teachingAssistantDTO.getResourceGroupNames().size() - 1);
            TeachingAssistant teachingAssistant = teachingAssistantDTO.retrieveTeachingAssistant();
            String username = teachingAssistant.getUsername();
            teachingAssistantsManagementService.addTeachingAssistant(teachingAssistant);
            teachingAssistantsManagementService.deleteAllTeachingAssistantResourceGroups(username);
            for (String groupName : teachingAssistantDTO.getResourceGroupNames()) {
                teachingAssistantsManagementService.addTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup(username, groupName));
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @PostMapping("/updateTeachingAssistantDTO")
    public String updateTeachingAssistantDTO(TeachingAssistantDTO teachingAssistantDTO) {
        try {
            //for some reason the data sent from front end contains an empty resource group that should be removed
            teachingAssistantDTO.getResourceGroupNames().remove(teachingAssistantDTO.getResourceGroupNames().size() - 1);
            TeachingAssistant teachingAssistant = teachingAssistantDTO.retrieveTeachingAssistant();
            String username = teachingAssistant.getUsername();
            teachingAssistantsManagementService.updateTeachingAssistant(teachingAssistant);
            teachingAssistantsManagementService.deleteAllTeachingAssistantResourceGroups(username);
            for (String groupName : teachingAssistantDTO.getResourceGroupNames()) {
                teachingAssistantsManagementService.addTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup(username, groupName));
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("/removeTeachingAssistantDTO")
    public String removeTeachingAssistantDTO(String username) {
        try {
            teachingAssistantsManagementService.removeTeachingAssistant(username);
            teachingAssistantsManagementService.deleteAllTeachingAssistantResourceGroups(username);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("/teachingAssistantDTOs")
    public List<TeachingAssistantDTO> teachingAssistantDTOs(){
        return teachingAssistantsManagementService.selectAllTeachingAssistantDTOs();
    }



}

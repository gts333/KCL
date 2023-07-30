package com.kcl.controller;

import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.po.TeachingAssistantResourceGroup;
import com.kcl.service.TeachingAssistantsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean addTeachingAssistantDTO(TeachingAssistantDTO teachingAssistantDTO) {
        return teachingAssistantsManagementService.addTeachingAssistant(teachingAssistantDTO.retrieveTeachingAssistant());
    }

    @GetMapping("/removeTeachingAssistantDTO")
    public boolean removeTeachingAssistantDTO(String username) {
        return teachingAssistantsManagementService.removeTeachingAssistant(username);
    }

    @PostMapping("/updateTeachingAssistantDTO")
    public boolean updateTeachingAssistantDTO(TeachingAssistantDTO teachingAssistantDTO) {
        return teachingAssistantsManagementService.updateTeachingAssistant(teachingAssistantDTO.retrieveTeachingAssistant());
    }

    @GetMapping("/teachingAssistantDTOs")
    public List<TeachingAssistantDTO> teachingAssistantDTOs(){
        return teachingAssistantsManagementService.selectAllTeachingAssistantDTOs();
    }

    @PostMapping("/addTeachingAssistantResourceGroup")
    public boolean addTeachingAssistantResourceGroup(TeachingAssistantResourceGroup teachingAssistantResourceGroup) {
        return teachingAssistantsManagementService.addTeachingAssistantResourceGroup(teachingAssistantResourceGroup);
    }

//    @PostMapping("/removeTeachingAssistantResourceGroup")
//    public boolean removeTeachingAssistantResourceGroup(TeachingAssistantResourceGroup teachingAssistantResourceGroup) {
//        return teachingAssistantsManagementService.removeTeachingAssistantResourceGroup(teachingAssistantResourceGroup);
//    }


}

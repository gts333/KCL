package com.kcl.controller;

import com.kcl.dto.TeachingAssistantDTO;
import com.kcl.po.TeachingAssistantResourceGroup;
import com.kcl.service.TeachingAssistantManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/teachingAssistants")
public class AdminTeachingAssistantManagementController {

    private TeachingAssistantManagementService teachingAssistantManagementService;

    @Autowired
    public AdminTeachingAssistantManagementController(TeachingAssistantManagementService teachingAssistantManagementService) {
        this.teachingAssistantManagementService = teachingAssistantManagementService;
    }

    @PostMapping("/addTeachingAssistantDTO")
    public boolean addTeachingAssistantDTO(TeachingAssistantDTO teachingAssistantDTO) {
        return teachingAssistantManagementService.addTeachingAssistant(teachingAssistantDTO.retrieveTeachingAssistant());
    }

//    @GetMapping("/removeTeachingAssistantDTO")
//    public boolean removeTeachingAssistantDTO(int userId) {
//        return teachingAssistantManagementService.removeTeachingAssistant(userId);
//    }

    @PostMapping("/updateTeachingAssistantDTO")
    public boolean updateTeachingAssistantDTO(TeachingAssistantDTO teachingAssistantDTO) {
        return teachingAssistantManagementService.updateTeachingAssistant(teachingAssistantDTO.retrieveTeachingAssistant());
    }

    @GetMapping("/teachingAssistantDTOs")
    public List<TeachingAssistantDTO> teachingAssistantDTOs(){
        return teachingAssistantManagementService.selectAllTeachingAssistantDTOs();
    }

    @PostMapping("/addTeachingAssistantResourceGroup")
    public boolean addTeachingAssistantResourceGroup(TeachingAssistantResourceGroup teachingAssistantResourceGroup) {
        return teachingAssistantManagementService.addTeachingAssistantResourceGroup(teachingAssistantResourceGroup);
    }

    @PostMapping("/removeTeachingAssistantResourceGroup")
    public boolean removeTeachingAssistantResourceGroup(TeachingAssistantResourceGroup teachingAssistantResourceGroup) {
        return teachingAssistantManagementService.removeTeachingAssistantResourceGroup(teachingAssistantResourceGroup);
    }


}

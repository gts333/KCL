package com.kcl.controller;

import com.kcl.po.ResourceGroup;
import com.kcl.service.ResourceGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/resourceGroups")
public class AdminResourceGroupsController {

    private ResourceGroupsService resourceGroupsService;

    @Autowired
    public AdminResourceGroupsController(ResourceGroupsService resourceGroupsService) {
        this.resourceGroupsService = resourceGroupsService;
    }

    @GetMapping("/resourceGroups")
    public List<ResourceGroup> resourceGroups() {
        return resourceGroupsService.selectAllResourceGroups();
    }

    @PostMapping("/addResourceGroup")
    public String addResourceGroup(ResourceGroup resourceGroup) {
        try {
            resourceGroupsService.addResourceGroup(resourceGroup);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("/removeResourceGroup")
    public String removeResourceGroup(String groupName) {
        try {
            resourceGroupsService.removeResourceGroup(groupName);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

}

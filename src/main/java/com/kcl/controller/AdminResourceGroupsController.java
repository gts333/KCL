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

    @GetMapping
    public List<ResourceGroup> resourceGroups() {
        return resourceGroupsService.selectAllResourceGroups();
    }

    @PostMapping("/addResourceGroup")
    public boolean addResourceGroup(ResourceGroup resourceGroup) {
        return resourceGroupsService.addResourceGroup(resourceGroup);
    }

    @GetMapping("/removeResourceGroup")
    public boolean removeResourceGroup(String groupName) {
        return resourceGroupsService.removeResourceGroup(groupName);
    }

}

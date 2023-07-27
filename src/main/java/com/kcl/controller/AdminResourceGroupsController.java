package com.kcl.controller;

import com.kcl.po.ResourceGroup;
import com.kcl.service.ResourceGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/resourceGroups")
public class AdminResourceGroupsController {

    private ResourceGroupService resourceGroupService;

    @Autowired
    public AdminResourceGroupsController(ResourceGroupService resourceGroupService) {
        this.resourceGroupService = resourceGroupService;
    }

    @GetMapping
    public List<ResourceGroup> resourceGroups() {
        return resourceGroupService.selectAllResourceGroups();
    }

//    @PostMapping("/addResourceGroup")
//    public boolean addResourceGroup(ResourceGroup resourceGroup) {
//        return resourceGroupService.addResourceGroup(resourceGroup.getGroupName());
//    }

//    @GetMapping("/removeResourceGroup")
//    public boolean removeResourceGroup(int id) {
//        return resourceGroupService.removeResourceGroup(id);
//    }

//    @PostMapping("/updateResourceGroup")
//    public boolean updateResourceGroup(ResourceGroup resourceGroup) {
//        return resourceGroupService.updateResourceGroupName(resourceGroup);
//    }
}

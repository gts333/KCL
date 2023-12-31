package com.kcl.service.impl;

import com.kcl.dao.ResourceGroupsDAO;
import com.kcl.po.ResourceGroup;
import com.kcl.service.ResourceGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceGroupsServiceImpl implements ResourceGroupsService {

    private ResourceGroupsDAO resourceGroupsDAO;

    @Autowired
    public ResourceGroupsServiceImpl(ResourceGroupsDAO resourceGroupsDAO) {
        this.resourceGroupsDAO = resourceGroupsDAO;
    }


    @Override
    public boolean addResourceGroup(ResourceGroup resourceGroup) {
        return resourceGroupsDAO.addResourceGroup(resourceGroup) > 0;
    }

    @Override
    public boolean removeResourceGroup(String groupName) {
        return resourceGroupsDAO.removeResourceGroup(groupName) > 0;
    }

    @Override
    public List<ResourceGroup> selectAllResourceGroups() {
        return resourceGroupsDAO.selectAllResourceGroups();
    }
}

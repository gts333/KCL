package com.kcl.service;

import com.kcl.po.ResourceGroup;

import java.util.List;

public interface ResourceGroupsService {

    boolean addResourceGroup(ResourceGroup resourceGroup);

    boolean removeResourceGroup(String groupName);

    List<ResourceGroup> selectAllResourceGroups();
}

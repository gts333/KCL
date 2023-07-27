package com.kcl.service;

import com.kcl.po.ResourceGroup;

import java.util.List;

public interface ResourceGroupService {

    boolean addResourceGroup(ResourceGroup resourceGroup);

    boolean removeResourceGroup(String groupName);

    List<ResourceGroup> selectAllResourceGroups();
}

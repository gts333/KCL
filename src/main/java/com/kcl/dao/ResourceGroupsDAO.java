package com.kcl.dao;

import com.kcl.po.ResourceGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ResourceGroupsDAO {

    int addResourceGroup(ResourceGroup resourceGroup);

    int removeResourceGroup(String groupName);

    List<ResourceGroup> selectAllResourceGroups();

}

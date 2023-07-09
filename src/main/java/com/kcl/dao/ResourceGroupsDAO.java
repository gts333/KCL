package com.kcl.dao;

import com.kcl.domain.ResourceGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ResourceGroupsDAO {


    int addResourceGroup(String groupName);

    int removeResourceGroup(int groupId);

    int updateResourceGroupName(ResourceGroup resourceGroup);

    List<ResourceGroup> selectAllResourceGroups();

    int checkExist(int groupId);

}

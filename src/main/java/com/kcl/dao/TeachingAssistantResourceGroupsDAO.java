package com.kcl.dao;

import com.kcl.po.TeachingAssistantResourceGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TeachingAssistantResourceGroupsDAO {

    int addTeachingAssistantResourceGroup(TeachingAssistantResourceGroup teachingAssistantResourceGroup);

    int removeTeachingAssistantResourceGroup(TeachingAssistantResourceGroup teachingAssistantResourceGroup);

    List<TeachingAssistantResourceGroup> selectTeachingAssistantResourceGroupByUserId(int userId);
}

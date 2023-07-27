package com.kcl.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProjectPropertiesDAO {

    boolean getAutoTeachingAssistantAllocationEnabled();

    int getAmountToTriggerAutoAllocation();

    int getPriorityTime();

    void updateAutoTeachingAssistantAllocationEnabled(boolean b);

    void updateAmountToTriggerAutoAllocation(int value);

    void updatePriorityTime(int time);
}

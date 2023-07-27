package com.kcl.dao;

import com.kcl.po.TeachingAssistantAvailableTime;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface TeachingAssistantAvailableTimesDAO {

    int addTeachingAssistantAvailableTime(TeachingAssistantAvailableTime teachingAssistantAvailableTime);

    int removeTeachingAssistantAvailableTime(int timeId);

    int updateTeachingAssistantAvailableTime(TeachingAssistantAvailableTime teachingAssistantAvailableTime);

    List<TeachingAssistantAvailableTime> selectTeachingAssistantAllTimesByTeachingAssistantUsername(String username);

}

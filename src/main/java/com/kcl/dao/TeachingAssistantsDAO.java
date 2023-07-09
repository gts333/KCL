package com.kcl.dao;

import com.kcl.domain.TeachingAssistant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface TeachingAssistantsDAO {

    int addTeachingAssistant(TeachingAssistant teachingAssistant);

    int removeTeachingAssistant(int userId);

    int updateTeachingAssistant(TeachingAssistant teachingAssistant);

    List<TeachingAssistant> selectAllTeachingAssistants();
}

package com.kcl.dao;

import com.kcl.interfaces.LoginAble;
import com.kcl.po.TeachingAssistant;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface TeachingAssistantsDAO extends LoginAble {

    int addTeachingAssistant(TeachingAssistant teachingAssistant);

    int removeTeachingAssistant(String username);

    //this method will not update password
    int updateTeachingAssistant(TeachingAssistant teachingAssistant);

    List<TeachingAssistant> selectAllTeachingAssistants();

}

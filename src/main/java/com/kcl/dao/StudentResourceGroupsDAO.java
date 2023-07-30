package com.kcl.dao;

import com.kcl.po.StudentResourceGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentResourceGroupsDAO {

    int addStudentResourceGroup(StudentResourceGroup studentResourceGroup);

    int deleteStudentAllResourceGroups(String username);

    List<StudentResourceGroup> selectStudentResourceGroupsByUsername(String username);

}

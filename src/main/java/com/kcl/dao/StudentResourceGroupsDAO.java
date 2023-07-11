package com.kcl.dao;

import com.kcl.po.StudentResourceGroup;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentResourceGroupsDAO {

    int addStudentResourceGroup(StudentResourceGroup studentResourceGroup);

    int deleteStudentResourceGroup(StudentResourceGroup studentResourceGroup);

    List<StudentResourceGroup> selectStudentResourceGroupsByUserId(int userId);


}

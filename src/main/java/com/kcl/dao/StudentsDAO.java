package com.kcl.dao;

import com.kcl.constant.PriorityStatusEnum;
import com.kcl.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface StudentsDAO {

    int addStudent(Student student);

    int removeStudent(int userId);

    int updateStudent(Student student);

    List<Student> selectAllStudents();

    List<Student> selectStudentsByPriorityStatus(PriorityStatusEnum priorityStatusEnum);

    List<Student> selectStudentsByUsername(String username);

}

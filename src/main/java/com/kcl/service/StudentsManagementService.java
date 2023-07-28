package com.kcl.service;

import com.kcl.constant.PriorityStatusEnum;
import com.kcl.dto.StudentDTO;
import com.kcl.po.ResourceGroup;
import com.kcl.po.Student;
import com.kcl.po.StudentResourceGroup;


import java.util.List;

public interface StudentsManagementService {

    boolean addStudent(Student student);

    boolean removeStudent(String username);

    //this method will not update password
    boolean updateStudent(Student student);

    List<Student> selectAllStudents();

    List<Student> selectStudentsByPriorityStatus(PriorityStatusEnum priorityStatusEnum);

    boolean addStudentResourceGroup(StudentResourceGroup studentResourceGroup);

    boolean deleteStudentResourceGroup(StudentResourceGroup studentResourceGroup);

    List<StudentResourceGroup> selectStudentResourceGroupsByUsername(String username);

    List<StudentDTO> selectAllStudentsDTOs();

}

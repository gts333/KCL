package com.kcl.service.impl;

import com.kcl.component.PasswordManager;
import com.kcl.constant.PriorityStatusEnum;
import com.kcl.dao.StudentResourceGroupsDAO;
import com.kcl.dao.StudentsDAO;
import com.kcl.dto.StudentDTO;
import com.kcl.po.Student;
import com.kcl.po.StudentResourceGroup;
import com.kcl.service.StudentsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentsManagementServiceImpl implements StudentsManagementService {

    private StudentsDAO studentsDAO;
    private StudentResourceGroupsDAO studentResourceGroupsDAO;
    private PasswordManager passwordManager;

    @Autowired
    public StudentsManagementServiceImpl(StudentsDAO studentsDAO, StudentResourceGroupsDAO studentResourceGroupsDAO, PasswordManager passwordManager) {
        this.studentsDAO = studentsDAO;
        this.studentResourceGroupsDAO = studentResourceGroupsDAO;
        this.passwordManager = passwordManager;
    }

    @Override
    public boolean addStudent(Student student) {
        student.setPassword(passwordManager.encode(student.getPassword()));
        return studentsDAO.addStudent(student) > 0;
    }

    @Override
    public boolean removeStudent(String username) {
        return studentsDAO.removeStudent(username) > 0;
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentsDAO.updateStudent(student) > 0;
    }

    @Override
    public Student selectStudentByUsername(String username) {
        return studentsDAO.selectStudentByUsername(username);
    }

    @Override
    public List<Student> selectAllStudents() {
        return studentsDAO.selectAllStudents();
    }

    @Override
    public List<Student> selectStudentsByPriorityStatus(PriorityStatusEnum priorityStatusEnum) {
        return studentsDAO.selectStudentsByPriorityStatus(priorityStatusEnum);
    }

    @Override
    public boolean addStudentResourceGroup(StudentResourceGroup studentResourceGroup) {
        return studentResourceGroupsDAO.addStudentResourceGroup(studentResourceGroup) > 0;
    }

    @Override
    public boolean deleteStudentAllResourceGroups(String username) {
        return studentResourceGroupsDAO.deleteStudentAllResourceGroups(username) > 0;
    }

    @Override
    public List<StudentResourceGroup> selectStudentResourceGroupsByUsername(String username) {
        return studentResourceGroupsDAO.selectStudentResourceGroupsByUsername(username);
    }

    @Override
    public List<StudentDTO> selectAllStudentsDTOs() {
        List<StudentDTO> entities = new ArrayList<>();
        List<Student> students = studentsDAO.selectAllStudents();
        for (Student student : students) {
            List<StudentResourceGroup> resourceGroups = studentResourceGroupsDAO.selectStudentResourceGroupsByUsername(student.getUsername());
            entities.add(new StudentDTO(student, resourceGroups));
        }
        return entities;
    }

}

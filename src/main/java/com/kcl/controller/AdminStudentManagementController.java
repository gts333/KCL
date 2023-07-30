package com.kcl.controller;

import com.kcl.dto.StudentDTO;
import com.kcl.po.Student;
import com.kcl.po.StudentResourceGroup;
import com.kcl.service.StudentsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/students")
public class AdminStudentManagementController {

    private StudentsManagementService studentsManagementService;

    @Autowired
    public AdminStudentManagementController(StudentsManagementService studentsManagementService) {
        this.studentsManagementService = studentsManagementService;
    }

    @PostMapping("/updateStudentDTO")
    public String updateStudentDTO(StudentDTO studentDTO) {
        //for some reason the data sent from front end contains an empty resource group that should be removed
        studentDTO.getResourceGroupNames().remove(studentDTO.getResourceGroupNames().size() - 1);
        Student student = studentDTO.retrieveStudent();
        String username = student.getUsername();
        try {
            studentsManagementService.updateStudent(student);
            studentsManagementService.deleteStudentAllResourceGroups(username);
            for (String groupName : studentDTO.getResourceGroupNames()) {
                studentsManagementService.addStudentResourceGroup(new StudentResourceGroup(username, groupName));
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @PostMapping("/addStudentDTO")
    public String addStudentDTO(StudentDTO studentDTO) {
        //for some reason the data sent from front end contains an empty resource group that should be removed
        studentDTO.getResourceGroupNames().remove(studentDTO.getResourceGroupNames().size() - 1);
        Student student = studentDTO.retrieveStudent();
        String username = student.getUsername();
        try {
            studentsManagementService.addStudent(student);
            studentsManagementService.deleteStudentAllResourceGroups(username);
            for (String groupName : studentDTO.getResourceGroupNames()) {
                studentsManagementService.addStudentResourceGroup(new StudentResourceGroup(username, groupName));
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("/removeStudentDTO")
    public String removeStudentDTO(String username) {
        try {
            studentsManagementService.removeStudent(username);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("/studentDTOs")
    public List<StudentDTO> studentDTOs() {
        return studentsManagementService.selectAllStudentsDTOs();
    }


}

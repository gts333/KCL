package com.kcl.controller;

import com.kcl.dto.StudentDTO;
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

    @PostMapping("/addStudentDTO")
    public boolean addStudentDTO(StudentDTO studentDTO) {
        return studentsManagementService.addStudent(studentDTO.retrieveStudent());
    }

    @GetMapping("/removeStudentDTO")
    public boolean removeStudentDTO(String username) {
        return studentsManagementService.removeStudent(username);
    }

    @PostMapping("/updateStudentDTO")
    public boolean updateStudentDTO(StudentDTO studentDTO) {
        return studentsManagementService.updateStudent(studentDTO.retrieveStudent());
    }

    @GetMapping("/studentDTOs")
    public List<StudentDTO> studentDTOs() {
        return studentsManagementService.selectAllStudentsDTOs();
    }

    @PostMapping("/addStudentResourceGroup")
    public boolean addStudentResourceGroup(StudentResourceGroup studentResourceGroup) {
        return studentsManagementService.addStudentResourceGroup(studentResourceGroup);
    }

    @PostMapping("/removeStudentResourceGroup")
    public boolean removeStudentResourceGroup(StudentResourceGroup studentResourceGroup) {
        return studentsManagementService.deleteStudentResourceGroup(studentResourceGroup);
    }

}

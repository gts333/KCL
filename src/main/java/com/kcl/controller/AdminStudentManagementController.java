package com.kcl.controller;

import com.kcl.dto.StudentDTO;
import com.kcl.po.StudentResourceGroup;
import com.kcl.service.StudentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/students")
public class AdminStudentManagementController {

    private StudentManagementService studentManagementService;

    @Autowired
    public AdminStudentManagementController(StudentManagementService studentManagementService) {
        this.studentManagementService = studentManagementService;
    }

    @PostMapping("/addStudentDTO")
    public boolean addStudentDTO(StudentDTO studentDTO) {
//        return studentManagementService.addStudent(studentDTO.retrieveStudent());
        return false;
    }

//    @GetMapping("/removeStudentDTO")
//    public boolean removeStudentDTO(int userId) {
//        return studentManagementService.removeStudent(userId);
//    }

    @PostMapping("/updateStudentDTO")
    public boolean updateStudentDTO(StudentDTO studentDTO) {
//        return studentManagementService.updateStudent(studentDTO.retrieveStudent());
        return false;
    }

    @GetMapping("/studentDTOs")
    public List<StudentDTO> studentDTOs() {
        return studentManagementService.selectAllStudentsDTOs();
    }

    @PostMapping("/addStudentResourceGroup")
    public boolean addStudentResourceGroup(StudentResourceGroup studentResourceGroup) {
        return studentManagementService.addStudentResourceGroup(studentResourceGroup);
    }

//    @PostMapping("/removeStudentResourceGroup")
//    public boolean removeStudentResourceGroup(StudentResourceGroup studentResourceGroup) {
//        return studentManagementService.removeStudentResourceGroup(studentResourceGroup);
//    }

}

package com.kcl.service;

import com.kcl.constant.IdentityEnum;
import com.kcl.constant.PriorityStatusEnum;
import com.kcl.po.Student;
import com.kcl.po.StudentResourceGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class StudentsManagementServiceTest {
    @Autowired
    StudentsManagementService service;

    @Test
    void addStudent() {
        Student student = new Student();
        student.setUsername("ppp");
        student.setPassword("oo");
        student.setIdentity(IdentityEnum.STUDENT);
        assert service.addStudent(student);
    }

    @Test
    void removeStudent() {
        assert service.removeStudent("bob");
    }

    @Test
    void updateStudent() {
        Student student = new Student();
        student.setUsername("bob");
        student.setIdentity(IdentityEnum.STUDENT);
        assert service.updateStudent(student);
    }

    @Test
    void selectAllStudents() {
        System.out.println(service.selectAllStudents());
    }

    @Test
    void selectStudentsByPriorityStatus() {
        System.out.println(service.selectStudentsByPriorityStatus(PriorityStatusEnum.PRIORITY));
    }

    @Test
    void addStudentResourceGroup() {
        assert service.addStudentResourceGroup(new StudentResourceGroup("name", "group1"));
    }

    @Test
    void deleteStudentResourceGroup() {
        assert service.deleteStudentResourceGroup(new StudentResourceGroup("lee", "group1"));
    }

    @Test
    void selectStudentResourceGroupsByUsername() {
        System.out.println(service.selectStudentResourceGroupsByUsername("cat"));
    }

    @Test
    void selectAllStudentsDTOs() {
        System.out.println(service.selectAllStudentsDTOs());
    }
}
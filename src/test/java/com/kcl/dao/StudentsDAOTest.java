package com.kcl.dao;

import com.kcl.constant.IdentityEnum;
import com.kcl.constant.PriorityStatusEnum;
import com.kcl.po.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Transactional
class StudentsDAOTest {
    @Autowired
    StudentsDAO dao;
    @Test
    public void addStudent() {
        Student student = new Student("uname", "up", IdentityEnum.STUDENT,  PriorityStatusEnum.DEFAULT);
        assert dao.addStudent(student) > 0;
    }

    @Test
    public void removeStudent() {
        assert dao.removeStudent("alice") > 0;
    }


    @Test
    public void updateStudent() {
        Student student = new Student();
        student.setUsername("alice");
        student.setPassword("ss");
        student.setIdentity(IdentityEnum.STUDENT);
        student.setPriorityStatus(PriorityStatusEnum.DEFAULT);
        assert dao.updateStudent(student) > 0;
    }

    @Test
    public void selectAllStudents() {
        System.out.println(dao.selectAllStudents());
    }

    @Test
    public void selectStudentsByPriorityStatus() {
        System.out.println(dao.selectStudentsByPriorityStatus(PriorityStatusEnum.PRIORITY));
    }

    @Test
    void getPassword() {
        System.out.println(dao.getPassword("bob"));
    }

    @Test
    void updatePassword() {
        assert dao.updatePassword("bob", "123456") > 0;
    }

    @Test
    void checkStudentExists() {
        assert dao.checkStudentExists("bob") > 0;
        assert dao.checkStudentExists("dsfasdfasd") == 0;
    }
}
package com.kcl.dao;

import com.kcl.po.StudentResourceGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class StudentResourceGroupsDAOTest {

    @Autowired
    StudentResourceGroupsDAO dao;

    @Test
    public void addStudentResourceGroup() {
        assert dao.addStudentResourceGroup(new StudentResourceGroup(1, 1)) > 0;
    }

    @Test
    public void deleteStudentResourceGroup() {
        assert dao.deleteStudentResourceGroup(new StudentResourceGroup(1, 1)) > 0;
    }


    @Test
    public void selectStudentResourceGroupsByUserId() {
        System.out.println(dao.selectStudentResourceGroupsByUserId(1));
    }
}
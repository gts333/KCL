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
        assert dao.addStudentResourceGroup(new StudentResourceGroup("lee", "group1")) > 0;
    }

    @Test
    public void deleteStudentResourceGroup() {
        assert dao.deleteStudentResourceGroup(new StudentResourceGroup("lee", "group1")) > 0;
    }


    @Test
    public void selectStudentResourceGroupsByUsername() {
        assert dao.selectStudentResourceGroupsByUsername("cat").size() > 0;
    }
}
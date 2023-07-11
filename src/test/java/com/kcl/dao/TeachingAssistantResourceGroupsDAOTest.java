package com.kcl.dao;

import com.kcl.po.TeachingAssistantResourceGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TeachingAssistantResourceGroupsDAOTest {

    @Autowired
    TeachingAssistantResourceGroupsDAO dao;

    @Test
    public void addTeachingAssistantResourceGroup() {
        assert dao.addTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup(4,4)) > 0;
    }

    @Test
    public void removeTeachingAssistantResourceGroup() {
        assert dao.removeTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup(1,1)) > 0;
    }

    @Test
    public void selectTeachingAssistantResourceGroupByUserId() {
        System.out.println(dao.selectTeachingAssistantResourceGroupByUserId(1));
        System.out.println("=====");
        System.out.println(dao.selectTeachingAssistantResourceGroupByUserId(2));
    }
}
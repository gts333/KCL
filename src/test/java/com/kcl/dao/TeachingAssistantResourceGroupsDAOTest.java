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
        assert dao.addTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup("oio","group2")) > 0;
    }

    @Test
    public void removeTeachingAssistantResourceGroup() {
        assert dao.removeTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup("delta","group1")) > 0;
    }


    @Test
    void selectTeachingAssistantResourceGroupByUsername() {
        assert dao.selectTeachingAssistantResourceGroupByUsername("gamma").size() > 0;
    }
}
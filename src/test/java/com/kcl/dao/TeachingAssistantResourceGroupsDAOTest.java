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
    void deleteAllTeachingAssistantResourceGroups() {
        assert dao.deleteAllTeachingAssistantResourceGroups("gamma") > 0;
    }

    @Test
    void deleteTeachingAssistantResourceGroup() {
        assert dao.deleteTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup("gamma", "group3")) > 0;
    }

    @Test
    void selectTeachingAssistantResourceGroupByUsername() {
        assert dao.selectTeachingAssistantResourceGroupByUsername("gamma").size() > 0;
    }



}
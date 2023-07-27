package com.kcl.dao;


import com.kcl.po.ResourceGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class ResourceGroupsDAOTest {

    @Autowired
    ResourceGroupsDAO dao;
    @Test
    public void addResourceGroup() {
        assert dao.addResourceGroup(new ResourceGroup("group4")) > 0;
    }

    @Test
    public void removeResourceGroup() {
        dao.addResourceGroup(new ResourceGroup("group4"));
        assert dao.removeResourceGroup("group4") > 0;
    }

    @Test
    public void selectAllResourceGroups() {
        List<ResourceGroup> list = dao.selectAllResourceGroups();
        System.out.println(list);
    }


}
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
        assert dao.addResourceGroup("g4") > 0;
    }

    @Test
    public void removeResourceGroup() {
        assert dao.removeResourceGroup(1) > 0;
        assert dao.removeResourceGroup(5) == 0;
    }

    @Test
    public void updateResourceGroupName() {
        assert dao.updateResourceGroupName(new ResourceGroup(1, "temp")) > 0;
    }

    @Test
    public void selectAllResourceGroups() {
        List<ResourceGroup> list = dao.selectAllResourceGroups();
        System.out.println(list);
    }

    @Test
    public void checkExist() {
        assert dao.checkExist(1) > 0;
        assert dao.checkExist(5) == 0;
    }

}
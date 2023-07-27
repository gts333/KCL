package com.kcl.service;

import com.kcl.po.ResourceGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ResourceGroupServiceTest {
    @Autowired
    ResourceGroupService service;

    @Test
    void addResourceGroup() {
        assert service.addResourceGroup(new ResourceGroup("group7"));
    }

    @Test
    void removeResourceGroup() {
        service.addResourceGroup(new ResourceGroup("group8"));
        assert service.removeResourceGroup("group8");
    }

    @Test
    void selectAllResourceGroups() {
        System.out.println(service.selectAllResourceGroups());
    }
}
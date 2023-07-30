package com.kcl.service;

import com.kcl.constant.IdentityEnum;
import com.kcl.po.TeachingAssistant;
import com.kcl.po.TeachingAssistantAvailableTime;
import com.kcl.po.TeachingAssistantResourceGroup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class TeachingAssistantsManagementServiceTest {

    @Autowired
    TeachingAssistantsManagementService service;

    @Test
    void addTeachingAssistant() {
        TeachingAssistant teachingAssistant = new TeachingAssistant();
        teachingAssistant.setUsername("dd");
        teachingAssistant.setPassword("ee");
        teachingAssistant.setIdentity(IdentityEnum.TEACHING_ASSISTANT);
        assert service.addTeachingAssistant(teachingAssistant);
    }

    @Test
    void removeTeachingAssistant() {
        assert service.removeTeachingAssistant("alpha");
    }

    @Test
    void updateTeachingAssistant() {
        TeachingAssistant teachingAssistant = new TeachingAssistant();
        teachingAssistant.setUsername("alpha");
        teachingAssistant.setPassword("ee");
        teachingAssistant.setIdentity(IdentityEnum.TEACHING_ASSISTANT);
        assert service.updateTeachingAssistant(teachingAssistant);
    }

    @Test
    void selectAllTeachingAssistants() {
        System.out.println(service.selectAllTeachingAssistants());
    }

    @Test
    void addTeachingAssistantResourceGroup() {
        assert service.addTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup("vv", "group1"));
    }

    @Test
    void deleteAllTeachingAssistantResourceGroups() {
        assert service.deleteAllTeachingAssistantResourceGroups("gamma");
    }

    void deleteTeachingAssistantResourceGroup() {
        assert service.deleteTeachingAssistantResourceGroup(new TeachingAssistantResourceGroup("gamma", "group3"));
    }

    @Test
    void selectTeachingAssistantResourceGroupByUsername() {
        System.out.println(service.selectTeachingAssistantResourceGroupByUsername("alpha"));
    }

    @Test
    void addTeachingAssistantAvailableTime() {
        TeachingAssistantAvailableTime time = new TeachingAssistantAvailableTime();
        time.setTimeId(111);
        time.setTime("dd");
        assert service.addTeachingAssistantAvailableTime(time);
    }

    @Test
    void removeTeachingAssistantAvailableTime() {
        assert service.removeTeachingAssistantAvailableTime(1);
    }

    @Test
    void updateTeachingAssistantAvailableTime() {
        TeachingAssistantAvailableTime time = new TeachingAssistantAvailableTime();
        time.setTimeId(1);
        time.setTime("dd");
        assert service.updateTeachingAssistantAvailableTime(time);
    }

    @Test
    void selectTeachingAssistantAllTimesByTeachingAssistantUsername() {
        System.out.println(service.selectTeachingAssistantAllTimesByTeachingAssistantUsername("alpha"));
    }

    @Test
    void selectAllTeachingAssistantDTOs() {
        System.out.println(service.selectAllTeachingAssistantDTOs());
    }


}
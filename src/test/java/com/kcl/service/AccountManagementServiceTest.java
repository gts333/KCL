package com.kcl.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class AccountManagementServiceTest {
    @Autowired
    AccountManagementService accountManagementService;

    @Test
    void updatePassword() {
        assert accountManagementService.updatePassword("ADMINISTRATOR", "admin", "123456", "123456789");
    }
}
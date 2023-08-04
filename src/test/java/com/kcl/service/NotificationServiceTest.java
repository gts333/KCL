package com.kcl.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class NotificationServiceTest {

    @Autowired
    NotificationService service;

    @Test
    void sendMessage() {
        service.sendMessage("hello there!");
    }
}
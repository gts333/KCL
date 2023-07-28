package com.kcl.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AutomatedRequestsAndAppointmentsUpdateServiceTest {

    @Autowired
    AutomatedRequestsAndAppointmentsUpdateService service;

    @Test
    void removeObsoleteRequestsAndAppointments() {
        service.removeObsoleteRequestsAndAppointments();
    }

    @Test
    void checkAndUpdateRequestQueue() {
        service.checkAndUpdateRequestQueue();
    }
}
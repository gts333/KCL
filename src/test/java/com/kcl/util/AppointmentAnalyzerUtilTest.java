package com.kcl.util;

import com.kcl.service.AppointmentsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class AppointmentAnalyzerUtilTest {

    @Autowired
    AppointmentsService appointmentsService;

    @Test
    void generateTimeStringsOfAppointment() {
        System.out.println(AppointmentAnalyzerUtil.generateTimeStringsOfAppointment(appointmentsService.selectAppointmentById(1)));
    }
}
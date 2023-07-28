package com.kcl.service;

import com.kcl.constant.AppointmentTypeEnum;
import com.kcl.po.Appointment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AppointmentServiceTest {

    @Autowired
    AppointmentService appointmentService;

    @Test
    void addAppointment() {
        assert appointmentService.addAppointment(new Appointment());
    }

    @Test
    void updateAppointment() {
        Appointment appointment = new Appointment.AppointmentBuilder()
                .buildId("bob","alpha","group1")
                .buildContents("", "", AppointmentTypeEnum.DEFAULT)
                .buildTime("01_01_01", "01_01_01", new Timestamp(System.currentTimeMillis()))
                .build();
        appointment.setAppointmentId(1);
        assert appointmentService.updateAppointment(appointment);
    }

    @Test
    void removeAppointment() {
        assert appointmentService.removeAppointment(2);
        assert !appointmentService.removeAppointment(4);
    }

    @Test
    void selectAllAppointments() {
        System.out.println(appointmentService.selectAllAppointments());
    }

    @Test
    void selectAppointmentsByStudentUsername() {
        System.out.println(appointmentService.selectAppointmentsByStudentUsername("bob"));
    }

    @Test
    void selectAppointmentsByTeachingAssistantUsername() {
        System.out.println(appointmentService.selectAppointmentsByTeachingAssistantUsername("alpha"));
    }
}
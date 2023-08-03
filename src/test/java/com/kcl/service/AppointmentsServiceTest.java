package com.kcl.service;

import com.kcl.constant.AppointmentTypeEnum;
import com.kcl.po.Appointment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@SpringBootTest
@Transactional
class AppointmentsServiceTest {

    @Autowired
    AppointmentsService appointmentsService;

    @Test
    void addAppointment() {
        assert appointmentsService.addAppointment(new Appointment());
    }

    @Test
    void updateAppointment() {
        Appointment appointment = new Appointment.AppointmentBuilder()
                .buildId("bob","alpha","group1")
                .buildContents("", "", AppointmentTypeEnum.DEFAULT)
                .buildTime("01_01_01", "01_01_01", new Timestamp(System.currentTimeMillis()))
                .build();
        appointment.setAppointmentId(1);
        assert appointmentsService.updateAppointment(appointment);
    }

    @Test
    void removeAppointment() {
        assert appointmentsService.removeAppointment(2);
        assert !appointmentsService.removeAppointment(4);
    }

    @Test
    void selectAllAppointments() {
        System.out.println(appointmentsService.selectAllAppointments());
    }

    @Test
    void selectAppointmentsByStudentUsername() {
        System.out.println(appointmentsService.selectAppointmentsByStudentUsername("bob"));
    }

    @Test
    void selectAppointmentsByTeachingAssistantUsername() {
        System.out.println(appointmentsService.selectAppointmentsByTeachingAssistantUsername("alpha"));
    }

    @Test
    void selectAppointmentById() {
        System.out.println(appointmentsService.selectAppointmentById(1));
    }
}
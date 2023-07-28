package com.kcl.dao;

import com.kcl.constant.AppointmentTypeEnum;
import com.kcl.po.Appointment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;


@SpringBootTest
@Transactional
class AppointmentsDAOTest {
    @Autowired
    private AppointmentsDAO dao;

    @Test
    public void addAppointment() {
        Appointment appointment = new Appointment.AppointmentBuilder()
                .buildId("bob","alpha","group1")
                .buildContents("", "", AppointmentTypeEnum.DEFAULT)
                .buildTime("01_01_01", "01_01_01", new Timestamp(System.currentTimeMillis()))
                .build();
        assert dao.addAppointment(appointment) > 0;
    }

    @Test
    public void updateAppointment() {
        Appointment appointment = new Appointment.AppointmentBuilder()
                .buildId("bob","alpha","group1")
                .buildContents("", "", AppointmentTypeEnum.DEFAULT)
                .buildTime("01_01_01", "01_01_01", new Timestamp(System.currentTimeMillis()))
                .build();
        appointment.setAppointmentId(1);
        assert dao.updateAppointment(appointment) > 0;
    }

    @Test
    public void removeAppointment() {
        assert dao.removeAppointment(1) > 0;
    }

    @Test
    public void selectAllAppointments() {
        System.out.println(dao.selectAllAppointments());
    }

    @Test
    void selectAppointmentsByStudentUsername() {
        assert dao.selectAppointmentsByStudentUsername("bob") != null;
    }

    @Test
    void selectAppointmentsByTeachingAssistantUsername() {
        assert dao.selectAppointmentsByTeachingAssistantUsername("alpha") != null;
    }
}
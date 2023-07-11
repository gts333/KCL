package com.kcl.dao;

import com.kcl.constant.AppointmentTypeEnum;
import com.kcl.po.Appointment;
import com.kcl.dto.TimeSlot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class AppointmentsDAOTest {
    @Autowired
    private AppointmentsDAO dao;

    @Test
    public void addAppointment() {
        Appointment appointment = new Appointment.AppointmentBuilder()
                .buildId(1,1,1)
                .buildContents("", "", AppointmentTypeEnum.DEFAULT)
                .buildTime(new TimeSlot(1,1,1), new TimeSlot(1,1,1))
                .build();
        assert dao.addAppointment(appointment) > 0;
    }

    @Test
    public void updateAppointment() {
        Appointment appointment = new Appointment.AppointmentBuilder()
                .buildId(1,1,1)
                .buildContents("", "", AppointmentTypeEnum.DEFAULT)
                .buildTime(new TimeSlot(1,1,1), new TimeSlot(1,1,1))
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
    public void selectAppointmentsByStudentId() {
        System.out.println(dao.selectAppointmentsByStudentId(1));
    }

    @Test
    public void selectAppointmentsByTeachingAssistantId() {
        System.out.println(dao.selectAppointmentsByTeachingAssistantId(1));
    }
}
package com.kcl.service.impl;

import com.kcl.dao.AppointmentsDAO;
import com.kcl.po.Appointment;
import com.kcl.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppointmentsServiceImpl implements AppointmentsService {


    private AppointmentsDAO appointmentsDAO;

    @Autowired
    public AppointmentsServiceImpl(AppointmentsDAO appointmentsDAO) {
        this.appointmentsDAO = appointmentsDAO;
    }


    @Override
    public boolean addAppointment(Appointment appointment) {
        return appointmentsDAO.addAppointment(appointment) > 0;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        return appointmentsDAO.updateAppointment(appointment) > 0;
    }

    @Override
    public boolean removeAppointment(int appointmentId) {
        return appointmentsDAO.removeAppointment(appointmentId) > 0;
    }

    @Override
    public List<Appointment> selectAllAppointments() {
        return appointmentsDAO.selectAllAppointments();
    }

    @Override
    public List<Appointment> selectAppointmentsByStudentUsername(String username) {
        return appointmentsDAO.selectAppointmentsByStudentUsername(username);
    }

    @Override
    public List<Appointment> selectAppointmentsByTeachingAssistantUsername(String username) {
        return appointmentsDAO.selectAppointmentsByTeachingAssistantUsername(username);
    }
}

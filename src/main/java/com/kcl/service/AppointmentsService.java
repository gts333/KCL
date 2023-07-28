package com.kcl.service;

import com.kcl.po.Appointment;

import java.util.List;

public interface AppointmentsService {

    boolean addAppointment(Appointment appointment);

    boolean updateAppointment(Appointment appointment);

    boolean removeAppointment(int appointmentId);

    List<Appointment> selectAllAppointments();

    List<Appointment> selectAppointmentsByStudentUsername(String username);

    List<Appointment> selectAppointmentsByTeachingAssistantUsername(String username);

}

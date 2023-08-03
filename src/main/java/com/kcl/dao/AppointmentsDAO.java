package com.kcl.dao;

import com.kcl.po.Appointment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AppointmentsDAO {

    int addAppointment(Appointment appointment);

    int updateAppointment(Appointment appointment);

    int removeAppointment(int appointmentId);

    Appointment selectAppointmentById(int appointmentId);

    List<Appointment> selectAllAppointments();

    List<Appointment> selectAppointmentsByStudentUsername(String username);

    List<Appointment> selectAppointmentsByTeachingAssistantUsername(String username);


}

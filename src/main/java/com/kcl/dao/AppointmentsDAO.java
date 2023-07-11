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

    List<Appointment> selectAllAppointments();

    List<Appointment> selectAppointmentsByStudentId(int studentId);

    List<Appointment> selectAppointmentsByTeachingAssistantId(int teachingAssistantId);


}

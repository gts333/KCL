package com.kcl.util;

import com.kcl.po.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentAnalyzerUtil {
    /**
     * returns all the times involved in an appointment
     * @param appointment an appointment
     * @return a list of time strings, please refer to TeachingAssistantAvailableTime for format
     */
    public static List<String> generateTimeStringsOfAppointment(Appointment appointment) {
        List<String> strings = new ArrayList<>();
        String startString = appointment.getStartTime();
        String endString = appointment.getEndTime();
        if (startString.equals(endString)) {
            strings.add(startString);
        } else {
            int startHour = Integer.parseInt(startString.substring(6, 8));
            int endHour = Integer.parseInt(endString.substring(6, 8));
            String header = startString.substring(0, 6);
            for (int i = startHour; i <= endHour; i++) {
                strings.add(header + '0' + i);
            }
        }
        return strings;
    }
}

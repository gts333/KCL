package com.kcl.util;


import java.time.LocalDateTime;

public class TimeIntervalCalculatorUtil {

    /**
     * returns whether the time difference(in hours) between now and the time indicated by the "timeString" is within the allowed
     * time interval "hours"
     * @param timeString the timeString of a certain time, refer to TeachingAssistantAvailableTime for details
     * @param hours the number of allowed hours
     * @return true if the calculated time dif is within the hours
     */
    public static boolean isTimeWithinAllowedHours(String timeString, int hours) {
        LocalDateTime localDateTime = LocalDateTime.now();
        int currentWeekday = localDateTime.getDayOfWeek().getValue();
        int currentHour = localDateTime.getHour();
        int timeWeekday = Integer.parseInt(timeString.substring(0, 2));
        int timeHour = Integer.parseInt(timeString.substring(3, 5));
        //if the provided timeString is sometime later than today
        if (currentWeekday < timeWeekday || (currentWeekday == timeWeekday && currentHour <= timeHour)) {
           int timeDif = (timeWeekday - currentWeekday) * 24 + timeHour - currentHour;
           return timeDif <= hours;
        } else {
            //illegal time
            return false;
        }
    }

}

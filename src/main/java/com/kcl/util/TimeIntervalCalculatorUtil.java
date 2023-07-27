package com.kcl.util;

import com.kcl.constant.ProjectConstants;

import java.time.LocalDateTime;

public class TimeIntervalCalculatorUtil {

    public static boolean isTimeWithinDefaultAllowedHours(String timeString) {
        return calculateHoursDif(timeString) <= ProjectConstants.DEFAULT_ALLOWED_BOOKING_HOURS;
    }

    public static boolean isTimeObsolete(String timeString) {
        return calculateHoursDif(timeString) <= 0;
    }

    private static int calculateHoursDif(String timeString) {
        LocalDateTime localDateTime = LocalDateTime.now();
        int currentWeekday = localDateTime.getDayOfWeek().getValue();
        int currentHour = localDateTime.getHour();
        int timeWeekday = Integer.parseInt(timeString.substring(0, 2));
        int timeHour = Integer.parseInt(timeString.substring(3, 5));
        if (currentWeekday <= timeWeekday && currentHour <= timeHour) {
            return (timeWeekday - currentWeekday) * 24 + timeHour - currentHour;
        } else {
            return -1;
        }
    }
}

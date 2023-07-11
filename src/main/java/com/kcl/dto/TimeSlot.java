package com.kcl.dto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

/**
 * This class represents the time slots for appointments.
 * The weekday should be a number ranging from 0 to 6 inclusive, each representing a weekday
 * The hour should be a number ranging from 0 to 23 inclusive, each representing an hour
 * The tenMinuteInterval should be a number ranging from 0 to 5 inclusive. E.g. 0 represents the first ten minutes of an hour
 * For example, weekday = 2, hour = 9, tenMinuteInterval = 0
 * represents the time interval
 * from Wednesday 10:00 to Wednesday 10:10
 */
@Data
@AllArgsConstructor
public class TimeSlot implements Serializable {
    @Setter(AccessLevel.NONE)
    private int weekday;
    @Setter(AccessLevel.NONE)
    private int hour;
    @Setter(AccessLevel.NONE)
    private int tenMinuteInterval;
    @Setter(AccessLevel.NONE)
    private String stringValue;

    public TimeSlot(int weekday, int hour, int tenMinuteInterval) {
        this.weekday = weekday;
        this.hour = hour;
        this.tenMinuteInterval = tenMinuteInterval;
        updateStringValue();
    }

    public TimeSlot(String stringValue) {
        setStringValue(stringValue);
    }

    private void setStringValue(String stringValue) {
        this.stringValue = stringValue;
        updateIntValues();
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
        updateStringValue();
    }

    public void setHour(int hour) {
        this.hour = hour;
        updateStringValue();
    }

    public void setTenMinuteInterval(int tenMinuteInterval) {
        this.tenMinuteInterval = tenMinuteInterval;
        updateStringValue();
    }

    private void updateStringValue() {
        this.stringValue = weekday + "_" + hour + "_" + tenMinuteInterval;
    }

    private void updateIntValues() {
        this.weekday = Integer.parseInt(stringValue.substring(0, 2));
        this.hour = Integer.parseInt(stringValue.substring(3, 5));
        this.tenMinuteInterval = Integer.parseInt(stringValue.substring(6));
    }
}

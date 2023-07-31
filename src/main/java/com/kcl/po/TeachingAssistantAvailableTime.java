package com.kcl.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * The available represents whether it is available to book.
 * The student user id represents the student that had reserved this time slot.
 *
 * The timeSlot is a string with the format "weekday_hour_tenMinuteIntervalOfAnHour"
 * Each value should have a length of 2 and zero filled
 * weekday is an integer between 1 and 7 inclusive
 * hour is an integer between 1 and 24 inclusive
 * tenMinuteIntervalOfAnHour between 1 and 6 inclusive
 *
 * example: 01_08_02 represents Monday, 8 o'clock's time interval 8:10 to 8:20
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachingAssistantAvailableTime implements Serializable {

    private int timeId;
    private String username;
    private String time;
    private boolean available = true;

}

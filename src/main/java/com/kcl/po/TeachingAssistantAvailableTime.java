package com.kcl.po;


import com.kcl.dto.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * The available represents whether it is available to book.
 * The student user id represents the student that had reserved this time slot.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachingAssistantAvailableTime implements Serializable {

    private int timeId;
    private int teachingAssistantUserId;
    private TimeSlot timeSlot;
    private int studentUserId;
    private boolean available = true;



}

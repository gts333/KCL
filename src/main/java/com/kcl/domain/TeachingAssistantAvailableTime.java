package com.kcl.domain;


import com.kcl.dto.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * The available represents whether it is available to book.
 * The student user id represents the student that had reserved this time slot.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachingAssistantAvailableTime{

    private int timeId;
    private int teachingAssistantUserId;
    private TimeSlot timeSlot;
    private int studentUserId;
    private boolean available = true;



}

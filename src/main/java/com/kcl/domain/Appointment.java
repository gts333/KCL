package com.kcl.domain;

import com.kcl.constant.AppointmentTypeEnum;
import com.kcl.dto.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    //primary key
    private int appointmentId;

    private int studentUserId;
    private int teachingAssistantUserId;
    private int groupId;
    private String title;
    private String content;
    private AppointmentTypeEnum appointmentType;
    private TimeSlot startTime;
    private TimeSlot endTime;

    private Appointment(AppointmentBuilder appointmentBuilder) {
        this.studentUserId = appointmentBuilder.studentUserId;
        this.teachingAssistantUserId = appointmentBuilder.teachingAssistantUserId;
        this.groupId = appointmentBuilder.groupId;
        this.title = appointmentBuilder.title;
        this.content = appointmentBuilder.content;
        this.appointmentType = appointmentBuilder.appointmentType;
        this.startTime = appointmentBuilder.startTime;
        this.endTime = appointmentBuilder.endTime;
    }

    public static class AppointmentBuilder {
        private int studentUserId;
        private int teachingAssistantUserId;
        private int groupId;
        private String title;
        private String content;
        private AppointmentTypeEnum appointmentType;
        private TimeSlot startTime;
        private TimeSlot endTime;

        public AppointmentBuilder buildId(int studentUserId, int teachingAssistantUserId, int groupId) {
            this.studentUserId = studentUserId;
            this.teachingAssistantUserId = teachingAssistantUserId;
            this.groupId = groupId;
            return this;
        }

        public AppointmentBuilder buildContents(String title, String content, AppointmentTypeEnum appointmentType) {
            this.title = title;
            this.content = content;
            this.appointmentType = appointmentType;
            return this;
        }

        public AppointmentBuilder buildTime(TimeSlot startTime, TimeSlot endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }
}

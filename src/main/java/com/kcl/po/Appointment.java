package com.kcl.po;

import com.kcl.constant.AppointmentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment implements Serializable {
    //primary key
    private int appointmentId;

    private String studentUsername;
    private String teachingAssistantUsername;
    private String groupName;
    private String title;
    private String content;
    private AppointmentTypeEnum appointmentType;
    private String startTime;
    private String endTime;

    private Appointment(AppointmentBuilder appointmentBuilder) {
        this.studentUsername= appointmentBuilder.studentUsername;
        this.teachingAssistantUsername = appointmentBuilder.teachingAssistantUsername;
        this.groupName = appointmentBuilder.groupName;
        this.title = appointmentBuilder.title;
        this.content = appointmentBuilder.content;
        this.appointmentType = appointmentBuilder.appointmentType;
        this.startTime = appointmentBuilder.startTime;
        this.endTime = appointmentBuilder.endTime;
    }

    public static class AppointmentBuilder {
        private String studentUsername;
        private String teachingAssistantUsername;
        private String groupName;
        private String title;
        private String content;
        private AppointmentTypeEnum appointmentType;
        private String startTime;
        private String endTime;

        public AppointmentBuilder buildId(String studentUsername, String teachingAssistantUsername, String groupName) {
            this.studentUsername = studentUsername;
            this.teachingAssistantUsername = teachingAssistantUsername;
            this.groupName = groupName;
            return this;
        }

        public AppointmentBuilder buildContents(String title, String content, AppointmentTypeEnum appointmentType) {
            this.title = title;
            this.content = content;
            this.appointmentType = appointmentType;
            return this;
        }

        public AppointmentBuilder buildTime(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }
}

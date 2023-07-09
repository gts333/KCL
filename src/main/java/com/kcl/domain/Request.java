package com.kcl.domain;

import com.kcl.constant.AppointmentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    //primary key
    private int requestId;

    private int studentId;
    //the number of 10-minute intervals, "2" means the request wants 20 minutes continuous appointment from an assistant
    private int groupId;
    private int timeIntervals;
    private String title;
    private String content;
    private AppointmentTypeEnum appointmentType;
    private Timestamp creationTime;

    private Request(RequestBuilder requestBuilder) {
        this.studentId = requestBuilder.studentId;
        this.groupId = requestBuilder.groupId;
        this.timeIntervals = requestBuilder.timeIntervals;
        this.title = requestBuilder.title;
        this.content = requestBuilder.content;
        this.appointmentType = requestBuilder.appointmentType;
        this.creationTime = requestBuilder.creationTime;
    }

    public static class RequestBuilder {
        private int studentId;
        private int groupId;
        private int timeIntervals;
        private String title;
        private String content;
        private AppointmentTypeEnum appointmentType;
        private Timestamp creationTime;

        public RequestBuilder buildBasics(int studentId, int groupId, int timeIntervals) {
            this.studentId = studentId;
            this.timeIntervals = timeIntervals;
            this.groupId = groupId;
            return this;
        }

        public RequestBuilder buildInfo(String title, String content, AppointmentTypeEnum appointmentType, Timestamp creationTime) {
            this.title = title;
            this.content = content;
            this.appointmentType = appointmentType;
            this.creationTime = creationTime;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }
}
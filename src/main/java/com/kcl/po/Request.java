package com.kcl.po;

import com.kcl.constant.AppointmentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request implements Serializable {
    //primary key
    private int requestId;

    private String studentUsername;
    private String groupName;
    //the number of 10-minute intervals, "2" means the request wants 20 minutes continuous appointment from an assistant
    private int timeIntervals;
    private String title;
    private String content;
    private AppointmentTypeEnum appointmentType;
    private Timestamp creationTime;

    private Request(RequestBuilder requestBuilder) {
        this.studentUsername = requestBuilder.studentUsername;
        this.groupName = requestBuilder.groupName;
        this.timeIntervals = requestBuilder.timeIntervals;
        this.title = requestBuilder.title;
        this.content = requestBuilder.content;
        this.appointmentType = requestBuilder.appointmentType;
        this.creationTime = requestBuilder.creationTime;
    }

    public static class RequestBuilder {
        private String studentUsername;
        private String groupName;
        private int timeIntervals;
        private String title;
        private String content;
        private AppointmentTypeEnum appointmentType;
        private Timestamp creationTime;

        public RequestBuilder buildBasics(String studentUsername, String groupName, int timeIntervals) {
            this.studentUsername = studentUsername;
            this.groupName = groupName;
            this.timeIntervals = timeIntervals;
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
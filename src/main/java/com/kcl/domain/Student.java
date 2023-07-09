package com.kcl.domain;


import com.kcl.constant.IdentityEnum;
import com.kcl.constant.PriorityStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    //primary key
    private int userId;

    private String username;
    private String password;
    private IdentityEnum identity;
    private PriorityStatusEnum priorityStatus;

    public Student(String username, String password, IdentityEnum identity, PriorityStatusEnum priorityStatus) {
        this.username = username;
        this.password = password;
        this.identity = identity;
        this.priorityStatus = priorityStatus;
    }
}

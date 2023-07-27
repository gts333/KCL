package com.kcl.po;


import com.kcl.constant.IdentityEnum;
import com.kcl.constant.PriorityStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class Student implements Serializable {
    //primary key
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

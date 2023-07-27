package com.kcl.po;


import com.kcl.constant.IdentityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TeachingAssistant implements Serializable {
    //primary key
    private String username;
    private String password;
    private IdentityEnum identity;
    private boolean available = true;
    private boolean adjustable = true;

    public TeachingAssistant(String username, String password, IdentityEnum identity, boolean available, boolean adjustable) {
        this.username = username;
        this.password = password;
        this.identity = identity;
        this.available = available;
        this.adjustable = adjustable;
    }
}

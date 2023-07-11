package com.kcl.po;


import com.kcl.constant.IdentityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class Administrator implements Serializable {
    //primary key
    private int userId;

    private String username;
    private String password;
    private IdentityEnum identity;

    public Administrator(String username, String password, IdentityEnum identity) {
        this.username = username;
        this.password = password;
        this.identity = identity;
    }
}

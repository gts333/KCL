package com.kcl.domain;


import com.kcl.constant.IdentityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator  {
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

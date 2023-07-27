package com.kcl.po;


import com.kcl.constant.IdentityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator implements Serializable {
    //primary key
    private String username;
    private String password;
    private IdentityEnum identity;

}

package com.kcl.dto;

import com.kcl.constant.IdentityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @NotEmpty(message = "username cannot be blank")
    private String username;
    @NotEmpty(message = "password cannot be blank")
    private String password;
    @NotEmpty(message = "identity cannot be blank")
    private String identityString;
}

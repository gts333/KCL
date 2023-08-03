package com.kcl.component.impl;

import com.kcl.component.PasswordManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordManagerBCryptImpl implements PasswordManager {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }


    @Override
    public boolean matches(String toVerify, String password) {
        return bCryptPasswordEncoder.matches(toVerify, password);
    }

}

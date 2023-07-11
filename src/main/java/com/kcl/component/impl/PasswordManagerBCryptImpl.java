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

    /**
     *
     * @param toVerify the plain text password to verify
     * @param password the encoded password
     * @return whether they match
     */
    @Override
    public boolean matches(String toVerify, String password) {
        return bCryptPasswordEncoder.matches(toVerify, password);
    }

}

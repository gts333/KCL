package com.kcl.component;





public interface PasswordManager {

    String encode(String password);

    boolean matches(String toVerify, String password);
}

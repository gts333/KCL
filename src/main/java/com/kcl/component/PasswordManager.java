package com.kcl.component;





public interface PasswordManager {

    /**
     * encode a password
     * @param password the plain text
     * @return the password
     */
    String encode(String password);

    /**
     *
     * @param toVerify the plain text password to verify
     * @param password the encoded password
     * @return whether they match
     */
    boolean matches(String toVerify, String password);
}

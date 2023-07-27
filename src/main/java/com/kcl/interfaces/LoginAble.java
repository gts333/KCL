package com.kcl.interfaces;

//this interface denotes that the class implementing it has the functionality to obtain or update a password based on username
public interface LoginAble {
    String getPassword(String username);

    int updatePassword(String username, String updatedPassword);
}

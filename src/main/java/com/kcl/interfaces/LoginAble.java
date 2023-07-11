package com.kcl.interfaces;

//this interface denotes that the class implementing it has the functionality to obtain a password based on some username
public interface LoginAble {
    String getPassword(String username);
}

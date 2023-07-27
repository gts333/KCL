package com.kcl.service;

public interface AccountManagementService {
    boolean updatePassword(String identityString, String username, String originalPassword, String updatedPassword);
}

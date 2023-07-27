package com.kcl.service.impl;

import com.kcl.component.PasswordManager;
import com.kcl.dao.AdministratorsDAO;
import com.kcl.dao.StudentsDAO;
import com.kcl.dao.TeachingAssistantsDAO;
import com.kcl.interfaces.LoginAble;
import com.kcl.service.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountManagementServiceImpl implements AccountManagementService {
    private AdministratorsDAO administratorsDAO;
    private StudentsDAO studentsDAO;
    private TeachingAssistantsDAO teachingAssistantsDAO;
    private PasswordManager passwordManager;

    @Autowired
    public AccountManagementServiceImpl(AdministratorsDAO administratorsDAO, StudentsDAO studentsDAO, TeachingAssistantsDAO teachingAssistantsDAO, PasswordManager passwordManager) {
        this.administratorsDAO = administratorsDAO;
        this.studentsDAO = studentsDAO;
        this.teachingAssistantsDAO = teachingAssistantsDAO;
        this.passwordManager = passwordManager;
    }

    @Override
    public boolean updatePassword(String identityString, String username, String originalPassword, String updatedPassword) {
        LoginAble dao;
        switch (identityString) {
            case "ADMINISTRATOR":
                dao = administratorsDAO;
                break;
            case "STUDENT":
                dao = studentsDAO;
                break;
            case "TEACHING_ASSISTANT":
                dao = teachingAssistantsDAO;
                break;
            default:
                return false;
        }
        String passwordFromTable = dao.getPassword(username);
        if (passwordFromTable == null || !passwordManager.matches(originalPassword, passwordFromTable)) {
            return false;
        } else {
            dao.updatePassword(username, passwordManager.encode(updatedPassword));
            return true;
        }
    }
}

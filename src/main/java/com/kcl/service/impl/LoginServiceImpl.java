package com.kcl.service.impl;

import com.kcl.component.PasswordManager;
import com.kcl.constant.IdentityEnum;
import com.kcl.constant.ProjectConstants;
import com.kcl.dao.AdministratorsDAO;
import com.kcl.dao.StudentsDAO;
import com.kcl.dao.TeachingAssistantsDAO;
import com.kcl.dto.User;
import com.kcl.dto.VerificationResult;
import com.kcl.interfaces.LoginAble;
import com.kcl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Service
public class LoginServiceImpl implements LoginService {

    private AdministratorsDAO administratorsDAO;
    private StudentsDAO studentsDAO;
    private TeachingAssistantsDAO teachingAssistantsDAO;
    private PasswordManager passwordManager;

    @Autowired
    public LoginServiceImpl(AdministratorsDAO administratorsDAO, StudentsDAO studentsDAO, TeachingAssistantsDAO teachingAssistantsDAO, PasswordManager passwordManager) {
        this.administratorsDAO = administratorsDAO;
        this.studentsDAO = studentsDAO;
        this.teachingAssistantsDAO = teachingAssistantsDAO;
        this.passwordManager = passwordManager;
    }

    @Override
    public VerificationResult login(User user) {
        return verify(user, "", false);
    }

    @Override
    public VerificationResult isLogin(HttpSession session, String expectedIdentityString) {
        User user = (User) session.getAttribute(ProjectConstants.SESSION_KEY);
        return verify(user, expectedIdentityString, true);
    }

    private VerificationResult verify(User user, String expectedIdentityString, boolean isVerifyingLoginStatus) {
        if (user == null) {
            return new VerificationResult("user not logged in", false);
        }
        LoginAble currentDAO;
        String username = user.getUsername();
        String password = user.getPassword();
        String identityString = user.getIdentityString();
        if (isVerifyingLoginStatus && !identityString.equals(expectedIdentityString)) {
            return new VerificationResult("wrong user privilege", false);
        }
        switch (identityString) {
            case "ADMINISTRATOR":
                currentDAO = administratorsDAO;
                break;
            case "STUDENT":
                currentDAO = studentsDAO;
                break;
            case "TEACHING_ASSISTANT":
                currentDAO = teachingAssistantsDAO;
                break;
            default:
                return new VerificationResult("user identity invalid", false);
        }
        String passwordFromTable = currentDAO.getPassword(username);
        if (passwordFromTable == null || passwordFromTable.equals("")) {
            return new VerificationResult("user details does not match", false);
        }
        if (passwordManager.matches(password, passwordFromTable)) {
            return new VerificationResult(isVerifyingLoginStatus ? "user logged in" : "user login success", true);
        } else {
            return new VerificationResult("user details does not match", false);
        }
    }

}

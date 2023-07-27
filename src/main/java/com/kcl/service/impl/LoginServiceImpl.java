package com.kcl.service.impl;

import com.kcl.component.PasswordManager;
import com.kcl.constant.ProjectConstants;
import com.kcl.dao.AdministratorsDAO;
import com.kcl.dao.StudentsDAO;
import com.kcl.dao.TeachingAssistantsDAO;
import com.kcl.dto.UserDTO;
import com.kcl.dto.VerificationResultDTO;
import com.kcl.interfaces.LoginAble;
import com.kcl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


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
    public VerificationResultDTO login(UserDTO userDTO) {
        return verify(userDTO, "", false);
    }

    @Override
    public VerificationResultDTO isLogin(HttpSession session, String expectedIdentityString) {
        UserDTO userDTO = (UserDTO) session.getAttribute(ProjectConstants.SESSION_KEY);
        return verify(userDTO, expectedIdentityString, true);
    }

    private VerificationResultDTO verify(UserDTO userDTO, String expectedIdentityString, boolean isVerifyingLoginStatus) {
        if (userDTO == null) {
            return new VerificationResultDTO("user not logged in", false);
        }
        LoginAble currentDAO;
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String identityString = userDTO.getIdentityString();
        if (isVerifyingLoginStatus && !identityString.equals(expectedIdentityString)) {
            return new VerificationResultDTO("wrong user privilege", false);
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
                return new VerificationResultDTO("user identity invalid", false);
        }
        String passwordFromTable = currentDAO.getPassword(username);
        if (passwordFromTable == null || passwordFromTable.equals("")) {
            return new VerificationResultDTO("user details does not match", false);
        }
        if (passwordManager.matches(password, passwordFromTable)) {
            return new VerificationResultDTO(isVerifyingLoginStatus ? "user logged in" : "user login success", true);
        } else {
            return new VerificationResultDTO("user details does not match", false);
        }
    }

}

package com.kcl.controller;

import com.kcl.component.PasswordManager;
import com.kcl.constant.ProjectConstants;
import com.kcl.dto.User;
import com.kcl.dto.VerificationResult;
import com.kcl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService, PasswordManager passwordManager) {
        this.loginService = loginService;
    }

    @PostMapping
    public String login(@Valid User user, BindingResult errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return errors.getFieldError().getDefaultMessage();
        }
        VerificationResult verificationResult = loginService.login(user);
        if (verificationResult.isSuccess()) {
            request.getSession().setAttribute(ProjectConstants.SESSION_KEY, user);
            return user.getIdentityString();
        } else {
            return verificationResult.getMessage();
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().setAttribute(ProjectConstants.SESSION_KEY, null);
    }


}

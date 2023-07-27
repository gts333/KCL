package com.kcl.controller;

import com.kcl.component.PasswordManager;
import com.kcl.constant.ProjectConstants;
import com.kcl.dto.UserDTO;
import com.kcl.dto.VerificationResultDTO;
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
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public String login(@Valid UserDTO userDTO, BindingResult errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return errors.getFieldError().getDefaultMessage();
        }
        VerificationResultDTO verificationResultDto = loginService.login(userDTO);
        if (verificationResultDto.isSuccess()) {
            request.getSession().setAttribute(ProjectConstants.SESSION_KEY, userDTO);
            return userDTO.getIdentityString();
        } else {
            return verificationResultDto.getMessage();
        }
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().setAttribute(ProjectConstants.SESSION_KEY, null);
    }


}

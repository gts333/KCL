package com.kcl.service;


import com.kcl.dto.UserDTO;
import com.kcl.dto.VerificationResultDTO;

import javax.servlet.http.HttpSession;


public interface LoginService {

    VerificationResultDTO isLogin(HttpSession session, String identityString);

    VerificationResultDTO login(UserDTO userDTO);
}

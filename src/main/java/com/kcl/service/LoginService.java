package com.kcl.service;


import com.kcl.dto.User;
import com.kcl.dto.VerificationResult;

import javax.servlet.http.HttpSession;


public interface LoginService {

    VerificationResult isLogin(HttpSession session, String identityString);

    VerificationResult login(User user);
}

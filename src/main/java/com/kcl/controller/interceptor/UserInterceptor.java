package com.kcl.controller.interceptor;


import com.kcl.dto.UserDTO;
import com.kcl.dto.VerificationResultDTO;
import com.kcl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class UserInterceptor implements HandlerInterceptor {

    private LoginService loginService;

    @Autowired
    public UserInterceptor(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO)session.getAttribute("user");
        if (userDTO == null) {
            request.getRequestDispatcher("/loginPage.html").forward(request, response);
            return false;
        }
        String requestedURI = request.getRequestURI();
        VerificationResultDTO result = null;
        if (requestedURI.startsWith("/privileged/admin") || requestedURI.startsWith("/admin")) {
            result = loginService.isLogin(session, "ADMINISTRATOR");
        } else if (requestedURI.startsWith("/privileged/student") || requestedURI.startsWith("/student")) {
            result = loginService.isLogin(session, "STUDENT");
        } else if (requestedURI.startsWith("/privileged/teachingAssistant") || requestedURI.startsWith("/teachingAssistant")) {
            result = loginService.isLogin(session, "TEACHING_ASSISTANT");
        }
        if (result == null || !result.isSuccess()) {
            request.getRequestDispatcher("/loginPage.html").forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}

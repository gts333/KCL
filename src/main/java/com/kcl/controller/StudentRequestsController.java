package com.kcl.controller;

import com.kcl.constant.ProjectConstants;
import com.kcl.dto.UserDTO;
import com.kcl.po.Request;
import com.kcl.service.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@RestController
@RequestMapping("/student/requests")
public class StudentRequestsController {

    private RequestsService requestsService;

    @Autowired
    public StudentRequestsController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    @PostMapping("/addRequest")
    public String addRequests(HttpServletRequest httpServletRequest, Request request) {
        try {
            UserDTO dto = (UserDTO) httpServletRequest.getSession().getAttribute(ProjectConstants.SESSION_KEY);
            String username = dto.getUsername();
            request.setStudentUsername(username);
            request.setCreationTime(new Timestamp(System.currentTimeMillis()));
            request.setTimeIntervals(request.getTimeIntervals() / 10);
            requestsService.addRequest(request);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }


}

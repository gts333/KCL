package com.kcl.controller;

import com.kcl.po.Request;
import com.kcl.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/requests")
public class AdminRequestsController {

    private RequestService requestService;

    @Autowired
    public AdminRequestsController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public List<Request> requests() {
        return requestService.selectAllRequests();
    }
}

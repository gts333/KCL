package com.kcl.controller;

import com.kcl.po.Request;
import com.kcl.service.RequestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/requests")
public class AdminRequestsController {

    private RequestsService requestsService;

    @Autowired
    public AdminRequestsController(RequestsService requestsService) {
        this.requestsService = requestsService;
    }

    @GetMapping
    public List<Request> requests() {
        return requestsService.selectAllRequests();
    }
}

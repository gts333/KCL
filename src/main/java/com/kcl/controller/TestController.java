package com.kcl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class TestController {
    @GetMapping
    public String getBook() {
        System.out.println("running");
        return "hi";
    }
}

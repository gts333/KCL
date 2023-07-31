package com.kcl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping(value = "/loginPage.html")
    public String loginPage() {
        return "loginPage";
    }

    @RequestMapping(value = "/privileged/adminHomepage.html")
    public String adminHomepage() {
        return "privileged/adminHomepage";
    }

    @RequestMapping(value = "/privileged/studentHomepage.html")
    public String studentHomepage() {
        return "privileged/studentHomepage";
    }

    @RequestMapping(value = "/privileged/teachingAssistantHomepage.html")
    public String teachingAssistantHomepage() {
        return "privileged/teachingAssistantHomepage";
    }

}

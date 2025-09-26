package com.example.alertingplatform.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/") // maps to http://localhost:8080/
    public String home() {
        return "Welcome to Alerting Notification Platform!";
    }
    @GetMapping("/alerts")
    public String alerts() {
        return "Here are all your alerts!";
    }

    @GetMapping("/notifications")
    public String notifications() {
        return "Check your notifications here!";
    }
}

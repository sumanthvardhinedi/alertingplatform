package com.example.alertingplatform.controller;

import com.example.alertingplatform.model.Alert;
import com.example.alertingplatform.service.AlertService;
import com.example.alertingplatform.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trigger_reminders")
public class TriggerController {

    private final AlertService alertService;
    private final NotificationService notificationService;

    public TriggerController(AlertService alertService, NotificationService notificationService) {
        this.alertService = alertService;
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> triggerReminders() {
        List<Alert> alerts = alertService.getAllAlerts();
        for (Alert alert : alerts) {
            notificationService.sendNotification(alert);
        }
        return ResponseEntity.ok("Reminders triggered for all alerts.");
    }
}

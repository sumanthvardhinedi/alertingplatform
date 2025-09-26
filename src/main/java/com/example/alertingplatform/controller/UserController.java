package com.example.alertingplatform.controller;

import com.example.alertingplatform.model.Alert;
import com.example.alertingplatform.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/alerts")
public class UserController {

    private final AlertService alertService;

    public UserController(AlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Alert>> getUserAlerts(@PathVariable String userId) {
        List<Alert> alerts = alertService.getAlertsForUser(userId);
        return ResponseEntity.ok(alerts);
    }

    @PutMapping("/{userId}/{alertId}/read")
    public ResponseEntity<String> markAlertRead(@PathVariable String userId, @PathVariable String alertId) {
        boolean updated = alertService.markAlertRead(userId, alertId);
        if (!updated) {
            return ResponseEntity.ok("No alert found to mark as read.");
        }
        return ResponseEntity.ok("Alert marked as read successfully.");
    }

    @PutMapping("/{userId}/{alertId}/snooze")
    public ResponseEntity<String> snoozeAlert(@PathVariable String userId, @PathVariable String alertId) {
        boolean updated = alertService.snoozeAlert(userId, alertId);
        if (!updated) {
            return ResponseEntity.ok("No alert found to snooze.");
        }
        return ResponseEntity.ok("Alert snoozed for today.");
    }
}

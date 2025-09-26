package com.example.alertingplatform.controller;

import com.example.alertingplatform.model.Alert;
import com.example.alertingplatform.service.AlertService;
import com.example.alertingplatform.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/alerts")
public class AdminController {
    private final AlertService alertService;
    private final NotificationService notificationService;

    public AdminController(AlertService alertService, NotificationService notificationService) {
        this.alertService = alertService;
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        Alert savedAlert = alertService.createAlert(alert);
        notificationService.sendNotification(savedAlert);
        return ResponseEntity.ok(savedAlert);
    }

    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {
        return ResponseEntity.ok(alertService.getAllAlerts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alert> updateAlert(@PathVariable String id, @RequestBody Alert updatedAlert) {
        Alert updated = alertService.updateAlert(id, updatedAlert);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable String id) {
        alertService.deleteAlert(id);
        return ResponseEntity.noContent().build();
    }
}

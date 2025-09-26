package com.example.alertingplatform.service;

import com.example.alertingplatform.model.Alert;
import com.example.alertingplatform.repository.InMemoryRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReminderService {

    private final InMemoryRepo repo;
    private final NotificationService notificationService;

    public ReminderService(InMemoryRepo repo, NotificationService notificationService) {
        this.repo = repo;
        this.notificationService = notificationService;
    }

    public void sendReminders() {
        List<Alert> alerts = repo.getAllAlerts();

        for (Alert alert : alerts) {
            if (alert.getExpiryTime().isAfter(LocalDateTime.now())) {

                notificationService.sendNotification(alert);
            }
        }
    }
}

package com.example.alertingplatform.scheduler;

import com.example.alertingplatform.model.Alert;
import com.example.alertingplatform.model.UserAlertPreference;
import com.example.alertingplatform.repository.InMemoryRepo;
import com.example.alertingplatform.service.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {

    private final InMemoryRepo repo;
    private final NotificationService notificationService;

    public ReminderScheduler(InMemoryRepo repo, NotificationService notificationService) {
        this.repo = repo;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 2)
    public void sendRecurringAlerts() {
        List<Alert> alerts = repo.getAllAlerts();
        for (Alert alert : alerts) {
            if (alert.getExpiryTime().isBefore(LocalDateTime.now())) {
                continue;
            }

            if (alert.getAudienceUserIds() != null) {
                for (String userId : alert.getAudienceUserIds()) {
                    UserAlertPreference pref = repo.getUserAlertPreference(userId, alert.getId());

                    if (pref != null && pref.isSnoozedToday()) continue;

                    notificationService.sendNotification(alert);
                }
            }
        }
    }
}

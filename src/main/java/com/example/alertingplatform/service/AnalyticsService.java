package com.example.alertingplatform.service;

import com.example.alertingplatform.model.Alert;
import com.example.alertingplatform.model.DeliveryLog;
import com.example.alertingplatform.model.UserAlertPreference;
import com.example.alertingplatform.repository.InMemoryRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    private final InMemoryRepo repo;

    public AnalyticsService(InMemoryRepo repo) {
        this.repo = repo;
    }

    public Map<String, Object> getAnalytics() {
        Map<String, Object> metrics = new HashMap<>();

        List<Alert> alerts = repo.getAllAlerts();
        List<DeliveryLog> deliveries = repo.getDeliveryLogs();

        metrics.put("totalAlerts", alerts.size());

        metrics.put("totalDeliveries", deliveries.size());

        long readCount = repo.getUserAlertPrefs().values().stream()
                .filter(UserAlertPreference::isRead)
                .count();
        metrics.put("readCount", readCount);

        long snoozedCount = repo.getUserAlertPrefs().values().stream()
                .filter(UserAlertPreference::isSnoozedToday)
                .count();
        metrics.put("snoozedCount", snoozedCount);

        Map<String, Long> severityBreakdown = new HashMap<>();
        alerts.forEach(alert -> {
            severityBreakdown.merge(alert.getSeverity(), 1L, Long::sum);
        });
        metrics.put("severityBreakdown", severityBreakdown);

        return metrics;
    }
}

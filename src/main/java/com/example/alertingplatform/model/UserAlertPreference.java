package com.example.alertingplatform.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserAlertPreference {
    private String userId;
    private String alertId;
    private boolean read;
    private LocalDateTime snoozedAt;

    public UserAlertPreference(String userId, String alertId) {
        this.userId = userId;
        this.alertId = alertId;
        this.read = false;
        this.snoozedAt = null;
    }

    public void markRead() {
        this.read = true;
    }

    public void snooze() {
        this.snoozedAt = LocalDateTime.now();
    }
    public boolean isSnoozedToday() {
        return snoozedAt != null && snoozedAt.toLocalDate().equals(LocalDate.now());
    }

    // getters and setters
    public String getUserId() { return userId; }
    public String getAlertId() { return alertId; }
    public boolean isRead() { return read; }
    public LocalDateTime getSnoozedAt() { return snoozedAt; }

}

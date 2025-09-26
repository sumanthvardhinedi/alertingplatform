package com.example.alertingplatform.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Alert {
    private final String id = UUID.randomUUID().toString();
    private String title;
    private String message;
    private String severity;
    private LocalDateTime startTime;
    private LocalDateTime expiryTime;

    private List<String> audienceUserIds;

    public Alert() {}

    public Alert(String title, String message, String severity, List<String> audienceUserIds) {
        this.title = title;
        this.message = message;
        this.severity = severity;
        this.audienceUserIds = audienceUserIds;
        this.startTime = LocalDateTime.now();
        this.expiryTime = this.startTime.plusDays(1);
    }
}

package com.example.alertingplatform.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class DeliveryLog {

    private final String id = UUID.randomUUID().toString();
    @Setter
    private String alertId;
    @Setter
    private String userId;
    @Setter
    private LocalDateTime deliveredAt = LocalDateTime.now();
    @Setter
    private DeliveryType channel;

    public DeliveryLog(Long id, String userId, DeliveryType email) {}

    public DeliveryLog(String alertId, String userId, DeliveryType channel) {
        this.alertId = alertId;
        this.userId = userId;
        this.channel = channel;
    }

}

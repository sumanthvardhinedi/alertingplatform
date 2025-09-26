package com.example.alertingplatform.service;

import com.example.alertingplatform.channel.Channel;
import com.example.alertingplatform.model.Alert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final List<Channel> channels;

    public NotificationService(List<Channel> channels) {
        this.channels = channels;
    }

    public void sendNotification(Alert alert) {
        if (alert.getAudienceUserIds() == null) return;
        for (String userId : alert.getAudienceUserIds()) {
            for (Channel channel : channels) {
                channel.sendAlert(alert, userId);
            }
        }
    }

}

package com.example.alertingplatform.channel;

import com.example.alertingplatform.model.Alert;

public interface Channel {
    void sendAlert(Alert alert, String userId);
}

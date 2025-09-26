package com.example.alertingplatform.channel;

import com.example.alertingplatform.model.Alert;
import com.example.alertingplatform.model.DeliveryLog;
import com.example.alertingplatform.model.DeliveryType;
import com.example.alertingplatform.repository.InMemoryRepo;
import org.springframework.stereotype.Component;

@Component
public class InAppChannel implements Channel {

    private final InMemoryRepo repo;

    public InAppChannel(InMemoryRepo repo) {
        this.repo = repo;
    }
    @Override
    public void sendAlert(Alert alert, String userId) {
        String msg = "SMS Alert to User " + userId + ": " + alert.getTitle();
        repo.addDeliveryLog(new DeliveryLog(alert.getId(), userId, DeliveryType.IN_APP));
        System.out.println(msg);
    }

}

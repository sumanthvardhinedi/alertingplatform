package com.example.alertingplatform.repository;

import com.example.alertingplatform.model.DeliveryLog;
import com.example.alertingplatform.model.Alert;
import com.example.alertingplatform.model.UserAlertPreference;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryRepo {

    @Getter
    private final Map<String, UserAlertPreference> userAlertPrefs = new HashMap<>();
    private final List<Alert> alerts = new ArrayList<>();

    @Getter
    private final List<DeliveryLog> deliveryLogs = new ArrayList<>();

    public void addAlert(Alert alert) {
        alerts.add(alert);
    }
    public Optional<Alert> getAlertById(String alertId) {
        return alerts.stream()
                .filter(a -> a.getId().equals(alertId))
                .findFirst();
    }


    public List<Alert> getAllAlerts() {
        return alerts;
    }

    public void addDeliveryLog(DeliveryLog log) {
        deliveryLogs.add(log);
    }

    public List<DeliveryLog> getDeliveryLogsByUser(String userId) {
        List<DeliveryLog> result = new ArrayList<>();
        for (DeliveryLog log : deliveryLogs) {
            if (log.getUserId().equals(userId)) {
                result.add(log);
            }
        }
        return result;
    }

    public void saveUserAlertPreference(UserAlertPreference pref) {
        String key = pref.getUserId() + "-" + pref.getAlertId();
        userAlertPrefs.put(key, pref);
    }

    public UserAlertPreference getUserAlertPreference(String userId, String alertId) {
        String key = userId + "-" + alertId;
        return userAlertPrefs.get(key);
    }

}

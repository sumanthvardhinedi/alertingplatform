package com.example.alertingplatform.service;

import com.example.alertingplatform.model.Alert;
import com.example.alertingplatform.model.UserAlertPreference;
import com.example.alertingplatform.repository.InMemoryRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    private final InMemoryRepo repo;

    public AlertService(InMemoryRepo repo) {
        this.repo = repo;
    }

    public Alert createAlert(Alert alert) {
        repo.addAlert(alert);
        return alert;
    }

    public List<Alert> getAllAlerts() {
        return new ArrayList<>(repo.getAllAlerts());
    }

    public Alert updateAlert(String id, Alert updatedAlert) {
        Optional<Alert> existing = repo.getAlertById(id);
        if (existing.isPresent()) {
            Alert alert = existing.get();
            alert.setTitle(updatedAlert.getTitle());
            alert.setMessage(updatedAlert.getMessage());
            alert.setSeverity(updatedAlert.getSeverity());
            alert.setAudienceUserIds(updatedAlert.getAudienceUserIds());
            return alert;
        }
        return null;
    }

    public void deleteAlert(String id) {
        repo.getAllAlerts().removeIf(a -> a.getId().equals(id));
    }

    public boolean markAlertRead(String userId, String alertId) {
        UserAlertPreference pref = repo.getUserAlertPreference(userId, alertId);
        if (pref != null) {
            pref.markRead();
            repo.saveUserAlertPreference(pref);
            return true;
        }
        return false;
    }

    public boolean snoozeAlert(String userId, String alertId) {
        UserAlertPreference pref = repo.getUserAlertPreference(userId, alertId);
        if (pref == null) {
            pref = new UserAlertPreference(userId, alertId);
        }
        pref.snooze();
        repo.saveUserAlertPreference(pref);
        return true;
    }

    public List<Alert> getAlertsForUser(String userId) {
        List<Alert> result = new ArrayList<>();
        for (Alert alert : repo.getAllAlerts()) {
            if (alert.getAudienceUserIds() != null && alert.getAudienceUserIds().contains(userId)) {
                result.add(alert);
            }
        }
        return result;
    }


}

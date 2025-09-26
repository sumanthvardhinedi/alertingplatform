package com.example.alertingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlertingNotificationPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlertingNotificationPlatformApplication.class, args);
    }
}

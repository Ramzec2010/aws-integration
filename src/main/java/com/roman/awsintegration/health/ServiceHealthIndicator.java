package com.roman.awsintegration.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

@Component
public class ServiceHealthIndicator implements HealthIndicator {
    @Autowired
    private BuildProperties buildProperties;

    private final String message_key = "Service A";

    @Override
    public Health health() {
        if (!isRunningServiceA()) {
            return Health.down().withDetail(message_key, "Not Available").build();
        }
        return Health.up()
                .withDetail(message_key, "Available")
                .withDetail("name", buildProperties.getName())
                .withDetail("version 2", buildProperties.getVersion())
                .withDetail("deployment date", buildProperties.getTime().toString())
                .build();

    }

    private Boolean isRunningServiceA() {
        Boolean isRunning = true;
        // Logic Skipped

        return isRunning;
    }
}

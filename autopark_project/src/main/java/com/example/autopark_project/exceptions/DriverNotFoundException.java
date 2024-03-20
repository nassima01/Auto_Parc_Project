package com.example.autopark_project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DriverNotFoundException extends RuntimeException {
    private final String serviceName;
    private final LocalDateTime timestamp;

    public DriverNotFoundException(String message, String serviceName) {
        super(message);
        this.serviceName = serviceName;
        this.timestamp = LocalDateTime.now();
    }

    public String getServiceName() {
        return serviceName;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Override getMessage() to include additional information
    @Override
    public String getMessage() {
        return "[" + timestamp + "] " + "[" + serviceName + "] " + super.getMessage();
    }
}

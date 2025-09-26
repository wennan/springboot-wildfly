package com.example.demo.transactions;

import java.time.OffsetDateTime;
import java.util.Map;

public class ApiError {

    private OffsetDateTime timestamp;
    private String message;
    private Map<String, String> details;

    public ApiError() {
    }

    public ApiError(OffsetDateTime timestamp, String message, Map<String, String> details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}

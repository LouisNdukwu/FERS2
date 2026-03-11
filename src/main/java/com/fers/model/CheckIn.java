package com.fers.model;

import java.time.LocalDateTime;

public class CheckIn {

    private int userId;
    private int incidentId;
    private User.Status status;
    private LocalDateTime timestamp;

    public CheckIn(int userId, int incidentId, User.Status status) {
        this.userId = userId;
        this.incidentId = incidentId;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    // ✅ MUST be public
    public int getUserId() {
        return userId;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public User.Status getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "CheckIn{" +
                "userId=" + userId +
                ", incidentId=" + incidentId +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}
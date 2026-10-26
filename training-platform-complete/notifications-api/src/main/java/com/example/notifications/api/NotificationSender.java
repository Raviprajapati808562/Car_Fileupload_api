package com.example.notifications.api;

public interface NotificationSender {
    void send(String recipient, String message);
}

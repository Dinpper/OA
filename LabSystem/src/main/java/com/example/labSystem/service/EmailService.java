package com.example.labSystem.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendSignOutReminderEmail(String to, String name) throws MessagingException;
}

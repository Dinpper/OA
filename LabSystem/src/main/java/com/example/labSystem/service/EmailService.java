package com.example.labSystem.service;

import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Map;

public interface EmailService {
    void sendSignOutReminderEmail(String to, String name) throws MessagingException;

    void sendReportReminderEmail(String to, String name) throws MessagingException;

    void sendDailyReportEmail(String to, List<Map<String, Object>> dailyReports) throws MessagingException;
}

package com.example.labSystem.service;

import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.dto.ReportTaskDto;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Map;

public interface EmailService {
    void sendSignOutReminderEmail(RecordDto dto) throws MessagingException;

    void sendReportReminderEmail(String to, String name) throws MessagingException;

    void sendDailyReportEmail(String to, List<ReportTaskDto> dailyReports) throws MessagingException;

    void sendWeeklyReportEmail(String to, List<ReportTaskDto> dailyReports) throws MessagingException;
}

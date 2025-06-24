package com.example.labSystem.service;

import com.example.labSystem.domain.Group;
import com.example.labSystem.dto.GroupUserDto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.dto.ReportMessageDto;
import com.example.labSystem.dto.ReportTaskDto;
import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Map;

public interface EmailService {
    void sendSignOutReminderEmail(RecordDto dto) throws MessagingException;

    void sendReportReminderEmail(String to, String name) throws MessagingException;

    void sendDailyReportEmail(String to, List<ReportMessageDto> dailyReports) throws MessagingException;

    void sendWeeklyReportEmail(String to, List<ReportMessageDto> dailyReports) throws MessagingException;

    Map<String, List<ReportMessageDto>> getEmailSenderMapping(String reportType) throws Exception;

    Map<Integer, ReportMessageDto> queryReportMessage(String reportType) throws Exception;
}

package com.example.labSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportTaskDto {
    private String userName;
    private String email;
    private String account;
    private List<ReportMessageDto> groupReports;
}

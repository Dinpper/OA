package com.example.labSystem.dto;

import lombok.Data;

@Data
public class ReportDto {
    private Integer reportId;

    private String userName;

    private String account;

    private String groupName;

    private String signDuration;

    private String reportDate;

    private String workContent;

    private String problems;

    private String plan;

    private String achievement;
}

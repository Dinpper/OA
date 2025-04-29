package com.example.labSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportMessageDto {
    private Integer groupId;

    private String groupName;

    private List<ReportDto> members;
}

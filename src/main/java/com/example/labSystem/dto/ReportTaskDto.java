package com.example.labSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportTaskDto {

    private String groupName;

    private List<ReportDto> members;
}

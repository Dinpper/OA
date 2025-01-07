package com.example.labSystem.dto;


import lombok.Data;

import java.util.List;

@Data
public class ReportByPageDto {
    private List<ReportDto> reportList;
}

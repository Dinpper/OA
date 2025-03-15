package com.example.labSystem.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ExcelDto {
    private Integer reportId;

    private String user;

    private String contSummary;

    private Date subTime;

    private String contPlan;

    private String achievement;
}

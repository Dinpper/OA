package com.example.labSystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RecordSonDto {
    private String name;

    private String day;

    private String month;

    private String reportDate;

    private BigDecimal signDuration;
}

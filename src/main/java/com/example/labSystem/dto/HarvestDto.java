package com.example.labSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class HarvestDto {
    private Integer harvestId;

    private Date reportDate;

    private String fileName;

    private String filePath;

    private String account;

    private String userName;
}

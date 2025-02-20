package com.example.labSystem.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Harvest {
    private Integer harvestId;

    private Date reportDate;

    private String fileName;

    private String filePath;

    private String account;
}

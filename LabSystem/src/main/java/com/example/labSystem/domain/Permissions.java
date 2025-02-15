package com.example.labSystem.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Permissions {
    private Integer permissionId;
    private Date inputDate;
    private Date updateDate;
    private String permissionName;
    private String permissionKey;
    private String description;
}
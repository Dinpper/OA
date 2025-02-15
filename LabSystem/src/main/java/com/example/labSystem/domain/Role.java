package com.example.labSystem.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer roleId;
    private Date inputDate;
    private Date updateDate;
    private String roleName;
    private String description;
}

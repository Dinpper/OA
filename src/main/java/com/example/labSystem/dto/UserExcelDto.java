package com.example.labSystem.dto;

import lombok.Data;

@Data
public class UserExcelDto {
    private String account;

    private String userName;

    private String groupName;

    private Integer sex;

    private String grade;

    private String phone;

    private String email;

    private String className;

    private String stuNumber;

    private Integer reportFlag;

    private String roleDescription;
}

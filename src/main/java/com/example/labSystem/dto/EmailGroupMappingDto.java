package com.example.labSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmailGroupMappingDto {

    private Integer id;

    private String inputDate;

    private String updateDate;

    private String account;

    private String userName;

    private String email;

    private Integer groupId;

    private List<EmailGroupDto> groupList;

}

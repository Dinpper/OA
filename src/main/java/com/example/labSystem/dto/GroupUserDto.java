package com.example.labSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupUserDto {

    private Integer groupId;

    private String groupName;

    List<String> userList;

    List<String> accountList;
}

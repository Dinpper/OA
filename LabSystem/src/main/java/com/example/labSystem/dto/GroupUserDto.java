package com.example.labSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupUserDto {
    private String groupName;

    List<String> userList;
}

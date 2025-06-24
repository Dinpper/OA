package com.example.labSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class BatchUpdateUserDto {
    private String operator;
    private List<UserDto> users;
}

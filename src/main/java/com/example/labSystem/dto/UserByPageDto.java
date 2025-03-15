package com.example.labSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserByPageDto {
    private Integer size;

    private Integer page;

    private Integer pageCount;

    private Integer dataCount;

    List<UserExcelDto> userList;
}

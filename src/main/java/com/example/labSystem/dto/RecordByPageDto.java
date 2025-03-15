package com.example.labSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecordByPageDto {
    private Integer size;

    private Integer page;

    private Integer pageCount;

    private Integer dataCount;

    List<RecordExcelDto> list;
}

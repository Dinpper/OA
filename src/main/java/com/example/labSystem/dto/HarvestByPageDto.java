package com.example.labSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class HarvestByPageDto {
    private Integer size;

    private Integer page;

    private Integer pageCount;

    private Integer dataCount;

    private List<HarvestDto> HarvestList;
}

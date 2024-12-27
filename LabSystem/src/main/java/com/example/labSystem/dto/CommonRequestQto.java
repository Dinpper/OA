package com.example.labSystem.dto;

import lombok.Data;

/**
 * 能源请求实体类
 */


@Data
public class CommonRequestQto {


    private String platformCode;

    private Integer parkingLotType;

    private Integer incomeType;

    private String queryDate;

    private Integer hour;

    private String startDate;

    private String endDate;

    private String city;

    private Integer type;

    private String year;

    private String month;

    private String thisMonth;

    private String lastMonth;

    private String thisYear;

    private String lastYear;

    private String companyName;

    private Integer page;

    private Integer size;

    private Integer offset;

    private String userNumList;

    private String radiantDataList;

    private String account;
}

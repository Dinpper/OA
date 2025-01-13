package com.example.labSystem.dto;

import lombok.Data;

/**
 * 能源请求实体类
 */


@Data
public class CommonRequestQto {


    private String userName;

    private String account;

    private String password;

    private String queryDate;

    private String groupName;

    private Integer hour;

    private String startDate;

    private String endDate;

    private Integer type;

    private String year;

    private String month;

    private String thisMonth;

    private String lastMonth;

    private String thisYear;

    private String lastYear;

    private Integer page;

    private Integer size;

    private Integer offset;

    private String workContent;

    private String problems;

    private String plan;
}

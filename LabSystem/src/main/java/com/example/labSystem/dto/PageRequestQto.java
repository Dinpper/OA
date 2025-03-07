package com.example.labSystem.dto;

import lombok.Data;

/**
 * 分页查询、 Excel导出
 *
 * @author Dinpper
 * @since 2025-01-15 11:03:49
 */
@Data
public class PageRequestQto {
    private String operator;

    private String userName;

    private String account;

    private String groupName;

    private String meetingName;

    private String fileName;

    private Integer sourceType;

    private Integer type;

    private String reportDate;

    private String startDate;

    private String endDate;

    private Integer state;

    private Integer page;

    private Integer size;

    private Integer offset;
}

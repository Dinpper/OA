package com.example.labSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LeaveDto {
    //id
    private Integer id;
    //上报时间
    private Date reportDate;
    //请假开始时间
    private Date startDate;
    //请假结束时间
    private Date endDate;
    //请假原因
    private String reason;
    //拒绝原因
    private String remarks;
    //0-不批准，1-批准，2-待审核
    private Integer allowedFlag;
    //请假人账号
    private String account;
    private String handlers;
}
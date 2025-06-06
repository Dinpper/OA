package com.example.labSystem.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Record implements Serializable {
    //签到记录编号
    private Integer recordId;
    //签到时间
    private Date startDate;
    //签退时间
    private Date endDate;
    //统计日期
    private Date reportDate;
    //签到时长
    private Integer signDuration;
    //状态类型（0未签退1已签退）
    private Integer statusType;
    private Integer isReminded;
    //用户账号
    private String account;
}

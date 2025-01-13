package com.example.labSystem.dto;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class RecordDto implements Serializable {
    private String name;

    private String groupName;
    //签到记录编号
    private Integer recordId;
    //签到时间
    private Date startDate;
    //签退时间
    private Date endDate;
    //统计日期
    private String reportDate;
    //签到时长
    private Double signDuration;
    //状态类型（0未签退1已签退）
    private Integer statusType;
    //用户账号
    private String account;

    private Double signDurationToday;

    private Double signDurationWeek;

    private Double signDurationMonth;

    private List<RecordSonDto> weekList;
}

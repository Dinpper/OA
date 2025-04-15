package com.example.labSystem.dto;


import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class RecordDto implements Serializable {
    //签到记录编号
    private Integer recordId;

    private String userName;

    private String email;

    private String groupName;

    //签到时间
    private String startDate;
    //签退时间
    private String endDate;
    //统计日期
    private String reportDate;
    //签到时长
    private BigDecimal signDuration;
    //状态类型（0未签退1已签退）
    private Integer statusType;
    //用户账号
    private String account;

    private Double signDurationToday;

    private Double signDurationWeek;

    private Double signDurationMonth;

    private List<RecordSonDto> weekList;
}

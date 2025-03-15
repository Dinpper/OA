package com.example.labSystem.dto;

import cn.idev.excel.annotation.ExcelIgnore;
import cn.idev.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RecordExcelDto {
    private String userName;

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

}

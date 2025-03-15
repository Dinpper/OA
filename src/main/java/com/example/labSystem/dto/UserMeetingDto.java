package com.example.labSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserMeetingDto {
    //记录ID
    private Integer userMeetingsId;
    //生成时间
    private Date inputDate;
    //更新时间
    private Date updateDate;
    //用户账号
    private String account;
    //会议ID
    private Integer meetingId;
    //状态（0：待定，1：接受，2：拒绝）
    private Integer status;
    //拒绝原因
    private String reason;
    //是否已通知（0：未通知，1：已通知）
    private Integer notifiedFlag;
}

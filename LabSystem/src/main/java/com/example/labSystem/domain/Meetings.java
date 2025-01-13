package com.example.labSystem.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Meetings implements Serializable {
    //会议ID
    private Integer meetingId;
    //生成时间
    private Date inputDate;
    //更新时间
    private Date updateDate;
    //会议名称
    private String meetingName;
    //会议描述
    private String description;
    //会议开始时间
    private Date startDate;
    //会议结束时间
    private Date endDate;
    //会议地点
    private String location;
    //主办方用户账号
    private String organizerAccount;
}
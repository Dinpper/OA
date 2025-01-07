package com.example.labSystem.domain;

import lombok.Data;
import java.util.Date;


@Data
public class Report {
    //报告编号
    private Integer reportId;
    //生成时间
    private Date inputDate;
    //更新时间
    private Date updateDate;
    //统计日期
    private Date reportDate;
    //工作内容
    private String workContent;
    //遇到问题
    private String problems;
    //计划
    private String plan;
    //附件路径集合
    private String achievement;
    //提交状态（0草稿1提交）
    private Integer submittedFlag;
    //账号
    private String account;
}

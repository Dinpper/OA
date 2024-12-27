package com.example.labSystem.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Users implements Serializable {
    //id
    private Integer userId;
    //创建时间
    private Date inputDate;
    //账号
    private String account;
    //密码
    private String password;
    //成员姓名
    private String name;
    //0-男，1-女
    private Integer sex;
    //电话
    private String phone;
    //所属年级
    private String grade;
    //邮箱
    private String email;
    //所属小组
    private String group;
    //删除标识(0:未删除; 1: 已删除)
    private Integer deleteFlag;
    //学号
    private String stuNumber;
    //班级
    private String className;

}
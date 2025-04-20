package com.example.labSystem.dto;

import lombok.Data;
import java.util.Date;

@Data
public class LoginDto {
    //id
    private Integer userId;
    //创建时间
    private Date inputDate;
    //修改时间
    private Date updateDate;
    //账号
    private String account;
    //密码
    private String password;
    //成员姓名
    private String userName;
    //0-男，1-女
    private Integer sex;
    //电话
    private String phone;
    //所属年级
    private String grade;
    //邮箱
    private String email;
    //所属小组
    private String groupName;
    //删除标识(0:未删除; 1: 已删除)
    private Integer deleteFlag;
    //是否上报标识（0不上报1上报）2
    private Integer reportFlag;
    //学号
    private String stuNumber;
    //班级
    private String className;

    private String role;

}

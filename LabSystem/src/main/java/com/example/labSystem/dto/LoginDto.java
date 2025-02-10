package com.example.labSystem.dto;

import lombok.Data;

import lombok.Data;

@Data
public class LoginDto {

    /**
     * 账户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码uuid
     */
    private String uuid;

    /**
     * 验证码
     */
    private String code;

    /**
     * 记住登录，默认为：true
     */
    private Boolean rememberMe = true;
}


package com.example.labSystem.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class SystemConfigDto {
    private Long id;  // 配置项ID

    private String configKey;  // 配置项的键（唯一标识）

    private String configValue;  // 配置项的值

    private String description;  // 配置项描述

    private Integer status;  // 状态: 0=禁用, 1=启用

    private Date createdAt;  // 创建时间

    private Date updatedAt;  // 更新时间
}

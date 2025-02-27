package com.example.labSystem.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Table(name = "system_config")
public class SystemConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 配置项ID

    @Column(name = "config_key", nullable = false, unique = true)
    private String configKey;  // 配置项的键（唯一标识）

    @Column(name = "config_value", nullable = false, columnDefinition = "TEXT")
    private String configValue;  // 配置项的值

    @Column(name = "description")
    private String description;  // 配置项描述

    @Column(name = "status", columnDefinition = "TINYINT(1) DEFAULT 1")
    private Integer status;  // 状态: 0=禁用, 1=启用

    @Column(name = "created_at", updatable = false)
    private Date createdAt;  // 创建时间

    @Column(name = "updated_at")
    private Date updatedAt;  // 更新时间
}
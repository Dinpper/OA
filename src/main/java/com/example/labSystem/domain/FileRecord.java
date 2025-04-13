package com.example.labSystem.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "file_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;

    @Column(name = "file_path", nullable = false, length = 255)
    private String filePath;

    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    @Column(name = "uploaded_by", nullable = false)
    private String uploadedBy;

    @Column(name = "uploaded_at", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private String uploadedAt;

    @Column(name = "file_type", length = 50)
    private String fileType;

    @Column(name = "visibility", nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Integer visibility;

    @Column(name = "source_type", nullable = false)
    private Integer sourceType;

    @Column(name = "related_id")
    private Integer relatedId;

    @Column(name = "stored_file_name", nullable = false, length = 255)
    private String storedFileName;  // 实际存储的UUID命名文件名

    @Column(name = "file_md5", length = 32)
    private String fileMd5;  // 文件内容的MD5值

    @Column(name = "download_count", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer downloadCount;  // 文件下载次数

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;  // 文件描述

    @Column(name = "deleted", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean deleted;  // 是否已删除（0：否，1：是）
}
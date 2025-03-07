package com.example.labSystem.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "files")
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
}
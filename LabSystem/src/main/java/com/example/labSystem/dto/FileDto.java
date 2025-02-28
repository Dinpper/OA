package com.example.labSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FileDto {
    private Integer id;

    private String fileName;

    private String filePath;

    private Long fileSize;

    private Integer uploadedBy;

    private String userName;

    private Date uploadedAt;

    private String fileType;

    private Integer visibility;

    private Integer sourceType;

    private Integer relatedId;


}

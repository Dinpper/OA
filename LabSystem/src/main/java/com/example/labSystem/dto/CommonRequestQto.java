package com.example.labSystem.dto;

import com.example.labSystem.domain.Course;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class CommonRequestQto {
    private String operator;

    private String userName;

    private String account;

    private String password;

    private String queryDate;

    private String groupName;

    private String leaderName;

    private Integer reportFlag;

    private Integer hour;

    private Integer type;

    private String year;

    private String month;

    private String workContent;

    private String problems;

    private String plan;

    private List<String> list;

    private List<Course> courseList;

    private List<MultipartFile> files;

    private String achievement;

    private Integer harvestId;
}

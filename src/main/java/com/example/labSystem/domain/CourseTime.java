package com.example.labSystem.domain;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class CourseTime {
    private Integer coursetimeId;
    private Date inputDate;
    private Date updateDate;
    private String date;
    private LocalTime firstStart;
    private LocalTime secondStart;
    private LocalTime thirdStart;
    private LocalTime fourthStart;
    private LocalTime fifthStart;
    private LocalTime sixthStart;
    private LocalTime seventhStart;
    private LocalTime eighthStart;
    private LocalTime ninthStart;
    private LocalTime tenthStart;
    private LocalTime eleventhStart;
    private LocalTime twelfthStart;
    private String duration;
}

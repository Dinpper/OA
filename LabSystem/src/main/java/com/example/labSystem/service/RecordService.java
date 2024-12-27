package com.example.labSystem.service;


import com.example.labSystem.dto.RecordDto;

public interface RecordService {

    RecordDto queryStatusType(String account);

    void attendanceCheckIn(String account);

    void attendanceCheckOut(String account);

}

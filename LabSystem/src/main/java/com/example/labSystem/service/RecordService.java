package com.example.labSystem.service;

import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.dto.RecordByPageDto;
import com.example.labSystem.dto.RecordDto;
import jakarta.servlet.http.HttpServletResponse;

public interface RecordService {

    RecordDto queryStatusType(String account);

    void attendanceCheckIn(String account);

    void attendanceCheckOut(String account);

    void download(HttpServletResponse response, PageRequestQto qto) throws Exception;

    RecordByPageDto queryRecordByPage(PageRequestQto qto) throws Exception;

}

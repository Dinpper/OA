package com.example.labSystem.service;

import com.example.labSystem.dto.PageRequestQto;
import com.example.labSystem.dto.RecordByPageDto;
import com.example.labSystem.dto.RecordDto;
import com.example.labSystem.dto.RecordSonDto;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordService {

    RecordDto queryStatusType(String account);

    void attendanceCheckIn(String account);

    void attendanceCheckOut(String account);

    void checkInRecordDownload(HttpServletResponse response, PageRequestQto qto);

    RecordByPageDto queryRecordByPage(PageRequestQto qto) throws Exception;

    List<RecordSonDto> querySignDurationWeek(String account);

    List<RecordSonDto> queryGroupSignDuration();

}
